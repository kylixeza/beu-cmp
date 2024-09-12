package com.kylix.navigation

import beukmm.navigator.SharedScreen
import cafe.adriel.voyager.core.registry.screenModule
import com.kylix.main.MainScreen

val mainNavigationScreenModule = screenModule {
    register<SharedScreen.Main> {
        MainScreen()
    }
}