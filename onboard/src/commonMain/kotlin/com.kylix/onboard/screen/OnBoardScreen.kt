package com.kylix.onboard.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import beukmm.components.LoadingDialog
import beukmm.navigator.SharedScreen
import beukmm.theme.Secondary500
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.kylix.onboard.components.FinishButton
import com.kylix.onboard.components.PageScreen
import com.kylix.onboard.components.PagerIndicator

class OnBoardScreen: Screen {

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val loginScreen = rememberScreen(SharedScreen.Login)

        val screenModel = koinScreenModel<OnBoardScreenModel>()
        val uiState = screenModel.uiState.collectAsState()
        val contents = screenModel.onBoardContent.collectAsState()

        val pagerState = rememberPagerState(pageCount = { contents.value.size })

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPager(
                modifier = Modifier.weight(8f),
                state = pagerState,
            ) {
                PageScreen(
                    content = contents.value[it]
                )
            }
            PagerIndicator(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterHorizontally),
                pagerState = pagerState,
                count = contents.value.size,
                selectedColor = Secondary500
            )
            FinishButton(
                modifier = Modifier.weight(1f),
                pagerState = pagerState,
                count = contents.value.size,
                onClick = {
                    screenModel.passOnBoarding()
                }
            )
        }

        LaunchedEffect(
            key1 = uiState.value?.isSuccess,
            block = {
                if (uiState.value?.isSuccess == true) {
                    navigator.replace(loginScreen)
                }
            }
        )

        if (uiState.value?.isLoading == true) {
            LoadingDialog(
                onDismissRequest = {
                    screenModel.onFinishLoading()
                }
            )
        }
    }
}