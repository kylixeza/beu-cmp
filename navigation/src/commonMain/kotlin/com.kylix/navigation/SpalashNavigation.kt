package com.kylix.navigation

import androidx.compose.runtime.Composable
import beukmm.navigator.SharedScreen
import cafe.adriel.voyager.core.registry.screenModule
import cafe.adriel.voyager.navigator.Navigator
import com.kylix.splash.screen.SplashScreen

val splashNavigationScreenModule = screenModule {
    register<SharedScreen.Splash> {
        SplashScreen()
    }
}

@Composable
fun AppNavigator() {
    Navigator(SplashScreen())
}