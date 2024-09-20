package beukmm.components

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext

@Composable
actual fun PlatformColors(
    statusBarColor: Color,
    navBarColor: Color
) {

    val activity = LocalContext.current as ComponentActivity
    val window = activity.window

    window.statusBarColor = statusBarColor.toArgb()
}