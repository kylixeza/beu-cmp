package beukmm.navigator

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed class SharedScreen: ScreenProvider {
    data object Splash : SharedScreen()
    data object OnBoard : SharedScreen()
    data object Login : SharedScreen()
    data object Register: SharedScreen()
    data object Main: SharedScreen()
    data class Detail(val recipeId: String): SharedScreen()
    data class Review(val historyId: String): SharedScreen()
}