package beukmm.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import beukmm.components.PlatformColors

@Composable
fun BeuTheme(
    content: @Composable () -> Unit
) {
    PlatformColors(
        statusBarColor = Primary700,
        navBarColor = BeuColors.primary
    )
    MaterialTheme(
        colors = BeuColors,
        typography = BeuTypography(),
        content = content
    )
}