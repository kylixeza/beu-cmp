package beukmm.navigator

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed class SharedScreen: ScreenProvider {
    data object Splash : SharedScreen()
    data object OnBoard : SharedScreen()
    data object Login : SharedScreen()
}