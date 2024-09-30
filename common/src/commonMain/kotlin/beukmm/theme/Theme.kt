package beukmm.theme

import androidx.compose.material3.MaterialTheme
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
        colorScheme = BeuColors,
        typography = BeuTypography(),
        content = content
    )
}