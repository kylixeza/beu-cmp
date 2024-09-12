package com.kylix.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import beukmm.base.BaseScreenContent
import beukmm.theme.White
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.kylix.main.components.TabNavigationItem
import com.kylix.main.screens.CameraTab
import com.kylix.main.screens.HomeTab
import com.kylix.main.screens.ProfileTab

class MainScreen : Screen {

    @Composable
    override fun Content() {

        TabNavigator(
            HomeTab,
            tabDisposable = {
                TabDisposable(
                    navigator = it,
                    tabs = listOf(HomeTab, CameraTab, ProfileTab)
                )
            }
        ) {
            BaseScreenContent(
                bottomBar = {
                    BottomNavigation(
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(80.dp),
                        elevation = 8.dp,
                        backgroundColor = White
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