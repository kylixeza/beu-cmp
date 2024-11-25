package com.kylix.profile.screens.history

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import beukmm.base.BaseScreenContent
import beukmm.components.BaseAppBar
import beukmm.components.EmptyListScreen
import beukmm.di.koinScreenModel
import beukmm.navigator.SharedScreen
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.kylix.profile.components.HistoryItem
import com.kylix.profile.components.ReviewPreviewBottomSheet
import com.multiplatform.lifecycle.LifecycleEvent
import com.multiplatform.lifecycle.LifecycleObserver
import com.multiplatform.lifecycle.LocalLifecycleTracker

class HistoryScreen: Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {

        val screenModel = koinScreenModel<HistoryScreenModel>()
        val historyState by screenModel.historyState.collectAsState()
        val uiState by screenModel.uiState.collectAsState()

        val bottomSheetState = rememberModalBottomSheetState()

        val navigator = LocalNavigator.currentOrThrow

        val lifecycleTracker = LocalLifecycleTracker.current

        BaseScreenContent(
            topBar = {
                BaseAppBar(
                    title = "Histories",
                    onLeftIconClick = { navigator.pop() },
                    rightIcon = null
                )
            },
            uiState = uiState,
            onLoadingDialogDismissRequest = { screenModel.onFinishLoading() }
        ) { innerPadding ->

            if (historyState.histories.isEmpty()) {
                EmptyListScreen(
                    modifier = Modifier.fillMaxSize(),
                    message = "Why don't you try some of our recipes? 🤔"
                )
            }

            LazyColumn(
                modifier = Modifier.fillMaxWidth().padding(innerPadding),
                contentPadding = PaddingValues(vertical = 12.dp)
            ) {
                items(
                    historyState.histories,
                    key = {
                        it.historyId + it.reviewRating
                    }
                ) {
                    HistoryItem(
                        history = it,
                        onGoToReviewPage = {
                            navigator.push(ScreenRegistry.get(SharedScreen.Review(it.historyId)))
                        },
                        onShowReview = {
                            screenModel.getReview(it.historyId)
                            screenModel.onShowBottomSheet()
                        }
                    )
                }
            }
        }

        if (historyState.showBottomSheet && historyState.selectedReview != null) {
            ReviewPreviewBottomSheet(
                modifier = Modifier,
                sheetState = bottomSheetState,
                review = historyState.selectedReview!!,
                onClose = {
                    screenModel.onHideBottomSheet()
                }
            )
        }

        DisposableEffect(Unit) {
            val listener =
                object : LifecycleObserver {
                    override fun onEvent(event: LifecycleEvent) {
                        if (event == LifecycleEvent.OnResumeEvent) {
                            screenModel.getHistories()
                        }
                    }
                }
            lifecycleTracker.addObserver(listener)
            onDispose {
                lifecycleTracker.removeObserver(listener)
            }
        }

    }

}