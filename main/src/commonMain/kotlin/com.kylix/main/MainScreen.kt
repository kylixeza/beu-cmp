package com.kylix.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import beukmm.base.BaseScreenContent
import beukmm.theme.White
import cafe.adriel.voyager.core.lifecycle.ScreenDisposable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.kylix.camera.CameraTab
import com.kylix.home.HomeTab
import com.kylix.main.components.TabNavigationItem
import com.kylix.profile.ProfileTab

class MainScreen : Screen, ScreenDisposable {

    @Composable
    override fun Content() {

        TabNavigator(
            HomeTab,
            disposeNestedNavigators = true,
        ) {
            BaseScreenContent(
                bottomBar = {
                    NavigationBar(
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(80.dp),
                        tonalElevation = 8.dp,
                        containerColor = White
                    ) {
                        TabNavigationItem(HomeTab)
                        TabNavigationItem(CameraTab)
                        TabNavigationItem(ProfileTab)
                    }
                }
            ) {
                CurrentTab()
            }
        }
    }

}