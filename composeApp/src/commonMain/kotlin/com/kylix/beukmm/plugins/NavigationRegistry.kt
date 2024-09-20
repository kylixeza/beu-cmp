package com.kylix.beukmm.plugins

import cafe.adriel.voyager.core.registry.ScreenRegistry
import com.kylix.navigation.authNavigationScreenModule
import com.kylix.navigation.detailNavigationScreenModule
import com.kylix.navigation.mainNavigationScreenModule
import com.kylix.navigation.onBoardNavigationScreenModule
import com.kylix.navigation.reviewNavigationScreenModule
import com.kylix.navigation.splashNavigationScreenModule

fun installNavigationRegistry() {
    ScreenRegistry {
        splashNavigationScreenModule()
        onBoardNavigationScreenModule()
        authNavigationScreenModule()
        mainNavigationScreenModule()
        detailNavigationScreenModule()
        reviewNavigationScreenModule()
    }
}