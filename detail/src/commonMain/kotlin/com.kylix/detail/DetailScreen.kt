package com.kylix.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import beukmm.base.BaseScreenContent
import beukmm.common.generated.resources.Res
import beukmm.common.generated.resources.ic_favorite
import beukmm.common.generated.resources.ic_unfavorite_white
import beukmm.components.BaseAppBar
import beukmm.di.koinScreenModel
import beukmm.theme.Error500
import beukmm.theme.Neutral900
import beukmm.theme.Primary700
import beukmm.theme.White
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.lifecycle.LifecycleEffectOnce
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.TabNavigator
import co.touchlab.kermit.Logger
import com.kylix.detail.components.DetailTabNavigation
import com.kylix.detail.components.VideoPlayer
import com.kylix.detail.tabs.AboutTab
import com.kylix.detail.tabs.InstructionTab
import com.kylix.detail.tabs.ReviewTab

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
                    rightIconTint = if (detailState.isFavorite) Error500 else White,
                    onRightIconClick = { screenModel.toggleFavorite(recipeId) }
                )
            },
            uiState = uiState,
            onLoadingDialogDismissRequest = {
                screenModel.onFinishLoading()
            },
            statusBarColor = Primary700
        ) {

            Column(
                modifier = Modifier.fillMaxSize()
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
                                Logger.i("Video Finished")
                            }
                        )
                    }

                    Text(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        text = "Source: ${detailState.recipe?.videoSrc.orEmpty()}",
                        style = MaterialTheme.typography.body2.copy(
                            fontSize = 10.sp
                        )
                    )

                    DetailTabNavigation(detailState)
                }
            }
        }
    }
}