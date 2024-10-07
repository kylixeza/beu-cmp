package com.kylix.navigation

import androidx.compose.runtime.Composable
import beukmm.base.ScaleTransition
import beukmm.navigator.SharedScreen
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.registry.screenModule
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.NavigatorDisposeBehavior
import cafe.adriel.voyager.transitions.ScreenTransition
import com.kylix.reset_password.ResetPasswordScreen
import com.kylix.splash.screen.SplashScreen

val splashNavigationScreenModule = screenModule {
    register<SharedScreen.Splash> {
        SplashScreen()
    }
}

@OptIn(ExperimentalVoyagerApi::class)
@Composable
fun AppNavigator() {
    Navigator(
        ResetPasswordScreen(),
        disposeBehavior = NavigatorDisposeBehavior(disposeSteps = false)
    ) { navigator ->
        ScreenTransition(
            navigator = navigator,
            defaultTransition = ScaleTransition(),
            disposeScreenAfterTransitionEnd = true
        )
    }
}

//test the review use this
//HISTORY-tmPSWr9LcO4D1m7pLWk3t