package beukmm.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun BeuTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = BeuColors,
        typography = BeuTypography(),
        content = content
    )
}