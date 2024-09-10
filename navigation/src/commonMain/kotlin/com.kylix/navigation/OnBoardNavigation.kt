package com.kylix.navigation

import beukmm.navigator.SharedScreen
import cafe.adriel.voyager.core.registry.screenModule
import com.kylix.onboard.screen.OnBoardScreen

val onBoardNavigationScreenModule = screenModule {
    register<SharedScreen.OnBoard> {
        OnBoardScreen()
    }
}