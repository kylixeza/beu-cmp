package com.kylix.navigation

import beukmm.navigator.SharedScreen
import cafe.adriel.voyager.core.registry.screenModule
import com.kylix.detail.DetailScreen

val detailNavigationScreenModule = screenModule {
    register<SharedScreen.Detail> {
        DetailScreen(it.recipeId)
    }
}