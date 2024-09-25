package com.kylix.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import beukmm.base.BaseScreenContent
import beukmm.theme.White
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.kylix.main.components.TabNavigationItem
import com.kylix.main.tabs.CameraTab
import com.kylix.main.tabs.HomeTab
import com.kylix.main.tabs.ProfileTab

class MainScreen : Screen {

    @Composable
    override fun Content() {

        var isVisible by remember { mutableStateOf(true) }

        val homeTab = remember { HomeTab(
            onNavigator = {
                isVisible = it
            }
        ) }
        val cameraTab = remember { CameraTab(
            onNavigator = {
                isVisible = it
            }
        ) }
        val profileTab = remember { ProfileTab }

        TabNavigator(
            homeTab,
            tabDisposable = {
                TabDisposable(
                    navigator = it,
                    tabs = listOf(homeTab, cameraTab, profileTab)
                )
            }
        ) {
            BaseScreenContent(
                bottomBar = {
                    AnimatedVisibility(
                        visible = isVisible,
                        enter = slideInVertically(initialOffsetY = { (it * 1.2).toInt() }),
                        exit = slideOutVertically(targetOffsetY = { (it * 1.2).toInt() })
                    ) {
                        BottomNavigation(
                            modifier = Modifier
                                .fillMaxWidth()
                                .size(80.dp),
                            elevation = 8.dp,
                            backgroundColor = White
                        ) {
                            TabNavigationItem(homeTab)
                            TabNavigationItem(cameraTab)
                            TabNavigationItem(profileTab)
                        }
                    }

                }
            ) {
                CurrentTab()
            }
        }
    }

}