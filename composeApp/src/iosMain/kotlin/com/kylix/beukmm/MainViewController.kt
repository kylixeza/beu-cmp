package com.kylix.beukmm

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.window.ComposeUIViewController
import com.kylix.beukmm.plugins.installKoin
import com.kylix.beukmm.plugins.installNavigationRegistry
import com.multiplatform.lifecycle.LifecycleTracker
import com.multiplatform.lifecycle.LocalLifecycleTracker
import com.multiplatform.lifecyle.LifecycleComposeUIVCDelegate
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    val lifecycleTracker = LifecycleTracker()

    return ComposeUIViewController(
        configure = {
            installKoin()
            installNavigationRegistry()
            delegate = LifecycleComposeUIVCDelegate(lifecycleTracker)
        },
    ) {
        CompositionLocalProvider(LocalLifecycleTracker provides lifecycleTracker) {
            App()
        }
    }
}