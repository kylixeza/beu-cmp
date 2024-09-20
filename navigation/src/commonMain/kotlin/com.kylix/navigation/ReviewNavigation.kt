package com.kylix.navigation

import beukmm.navigator.SharedScreen
import cafe.adriel.voyager.core.registry.screenModule
import com.kylix.review.ReviewScreen

val reviewNavigationScreenModule = screenModule {
    register<SharedScreen.Review> {
        ReviewScreen(it.historyId)
    }
}