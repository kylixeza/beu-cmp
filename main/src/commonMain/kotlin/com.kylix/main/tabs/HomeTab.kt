package com.kylix.main.tabs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.kylix.home.HomeScreen
import compose.icons.feathericons.Home
import kotlin.jvm.Transient

class HomeTab(
    @Transient
    val onNavigator: (isRoot: Boolean) -> Unit
): Tab {

    @Composable
    override fun Content() {

        Navigator(HomeScreen()) { navigator ->
            LaunchedEffect(navigator.lastItem) {
                onNavigator(navigator.lastItem is HomeScreen)
            }
            CurrentScreen()
        }
    }

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(image = compose.icons.FeatherIcons.Home)

            return remember {
                TabOptions(
                    index = 0u,
                    icon = icon,
                    title = "Home"
                )
            }
        }
}