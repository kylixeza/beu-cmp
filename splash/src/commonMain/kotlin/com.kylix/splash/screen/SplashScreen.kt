package com.kylix.splash.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import beukmm.common.generated.resources.Res
import beukmm.common.generated.resources.beu_icon
import beukmm.di.koinScreenModel
import beukmm.navigator.SharedScreen
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.annotation.InternalVoyagerApi
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource

class SplashScreen : Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val onboardScreen = rememberScreen(SharedScreen.OnBoard)
        val loginScreen = rememberScreen(SharedScreen.Login)
        val mainScreen = rememberScreen(SharedScreen.Main)

        val screenModel = koinScreenModel<SplashScreenModel>()
        val route = screenModel.route.collectAsState().value

        LaunchedEffect(route) {
            if (route != null) {
                delay(1000L)

                navigator.replace(
                    when (route) {
                        Route.ONBOARD -> onboardScreen
                        Route.LOGIN -> loginScreen
                        Route.MAIN -> mainScreen
                    }
                )
            }
        }

        Image(
            modifier = Modifier.fillMaxSize()
                .graphicsLayer {
                    scaleX = 0.25f
                    scaleY = 0.25f
                },
            painter = painterResource(Res.drawable.beu_icon),
            contentDescription = "App Icon"
        )
    }

}