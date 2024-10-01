package com.kylix.beukmm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.tooling.preview.Preview
import com.multiplatform.lifecycle.LifecycleTracker
import com.multiplatform.lifecycle.LocalLifecycleTracker
import com.multiplatform.lifecyle.AndroidLifecycleEventObserver

class MainActivity : ComponentActivity() {

    private val lifecycleTracker = LifecycleTracker()
    private val observer = AndroidLifecycleEventObserver(lifecycleTracker)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(observer)

        setContent {
            CompositionLocalProvider(LocalLifecycleTracker provides lifecycleTracker) {
                App()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(observer)
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}