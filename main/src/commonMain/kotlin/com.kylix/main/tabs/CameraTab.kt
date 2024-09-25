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
import com.kylix.camera.CameraScreen
import compose.icons.feathericons.Camera
import kotlin.jvm.Transient

class CameraTab(
    @Transient
    val onNavigator: (isRoot: Boolean) -> Unit
): Tab {

    @OptIn(ExperimentalVoyagerApi::class)
    @Composable
    override fun Content() {
        Navigator(
            CameraScreen(),
            disposeBehavior = NavigatorDisposeBehavior(disposeSteps = false)
        ) { navigator ->
            LaunchedEffect(navigator.lastItem) {
                onNavigator(navigator.lastItem is CameraScreen)
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
            val icon = rememberVectorPainter(image = compose.icons.FeatherIcons.Camera)

            return remember {
                TabOptions(
                    index = 1u,
                    icon = icon,
                    title = "Camera"
                )
            }
        }
}