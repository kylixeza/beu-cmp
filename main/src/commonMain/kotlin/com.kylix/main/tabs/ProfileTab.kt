package com.kylix.main.tabs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import beukmm.base.ScaleTransition
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.NavigatorDisposeBehavior
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.ScreenTransition
import com.kylix.profile.ProfileScreen
import compose.icons.feathericons.User
import kotlin.jvm.Transient

class ProfileTab(
    @Transient
    val onNavigator: (isRoot: Boolean) -> Unit
): Tab {

    @OptIn(ExperimentalVoyagerApi::class)
    @Composable
    override fun Content() {
        Navigator(
            ProfileScreen(),
            disposeBehavior = NavigatorDisposeBehavior(disposeSteps = false)
        ) { navigator ->
            LaunchedEffect(navigator.lastItem) {
                onNavigator(navigator.lastItem is ProfileScreen)
            }

            ScreenTransition(
                navigator = navigator,
                defaultTransition = ScaleTransition(),
                disposeScreenAfterTransitionEnd = true
            )
        }
    }

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(image = compose.icons.FeatherIcons.User)

            return remember {
                TabOptions(
                    index = 2u,
                    icon = icon,
                    title = "Profile"
                )
            }
        }
}