package com.kylix.beukmm

import androidx.compose.ui.window.ComposeUIViewController
import com.kylix.beukmm.plugins.installKoin
import com.kylix.beukmm.plugins.installNavigationRegistry

fun MainViewController() = ComposeUIViewController(
    configure = {
        installKoin()
        installNavigationRegistry()
    }
) {
    App()
}