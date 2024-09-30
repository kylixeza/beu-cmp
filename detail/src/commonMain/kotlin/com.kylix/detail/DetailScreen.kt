package com.kylix.detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import beukmm.base.BaseScreenContent
import beukmm.common.generated.resources.Res
import beukmm.common.generated.resources.ic_favorite
import beukmm.common.generated.resources.ic_unfavorite_white
import beukmm.components.BaseAppBar
import beukmm.di.koinScreenModel
import beukmm.navigator.SharedScreen
import beukmm.theme.Black
import beukmm.theme.Error500
import beukmm.theme.White
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.lifecycle.LifecycleEffectOnce
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.kylix.detail.components.DetailTabNavigation
import com.kylix.detail.components.ReviewSnacker
import com.kylix.detail.components.VideoPlayer

class DetailScreen(
    private val recipeId: String
): Screen {

    @OptIn(ExperimentalVoyagerApi::class)
    @Composable
    override fun Content() {

        val screenModel = koinScreenModel<DetailScreenModel>()
        val detailState by screenModel.detailState.collectAsState()
        val uiState by screenModel.uiState.collectAsState()

        LifecycleEffectOnce {
            screenModel.getRecipeDetail(recipeId)
        }

        val navigator = LocalNavigator.currentOrThrow

        BaseScreenContent(
            topBar = {
                BaseAppBar(
                    title = detailState.recipe?.name.orEmpty(),
                    onLeftIconClick = { navigator.pop() },
                    rightIcon = if (detailState.isFavorite) Res.drawable.ic_favorite else Res.drawable.ic_unfavorite_white,
                    rightIconTint = if (detailState.isFavorite) Error500 else Black,
                    onRightIconClick = { screenModel.toggleFavorite(recipeId) }
                )
            },
            uiState = uiState,
            onLoadingDialogDismissRequest = {
                screenModel.onFinishLoading()
            },
        ) { innerPadding ->
            Column(
                modifier = Modifier.fillMaxSize().padding(innerPadding)
            ) {
                if (detailState.recipe != null) {
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .height(210.dp),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        VideoPlayer(
                            modifier = Modifier.fillMaxSize(),
                            url = detailState.recipe?.video.orEmpty(),
                            onVideoFinished = {
                                screenModel.onVideoFinished()
                                screenModel.postHistory(recipeId)
                            }
                        )
                    }

                    Text(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        text = "Source: ${detailState.recipe?.videoSrc.orEmpty()}",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 10.sp
                        )
                    )

                    DetailTabNavigation(detailState)
                }
            }
        }

        AnimatedVisibility(
            visible = detailState.showReviewSnacker,
            enter = slideInVertically(
                initialOffsetY = { -it }
            ),
            exit = slideOutVertically(
                targetOffsetY = { -it }
            )
        ) {
            ReviewSnacker(
                cookingSessionDuration = detailState.cookingSessionTime,
                onCloseReview = {
                    screenModel.onCloseReviewSnacker()
                },
                onReviewNow = {
                    if (detailState.historyId != null)
                        navigator.replace(ScreenRegistry.get(SharedScreen.Review(detailState.historyId.orEmpty())),)
                }
            )
        }
    }
}