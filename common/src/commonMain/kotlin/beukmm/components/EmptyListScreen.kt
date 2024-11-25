package beukmm.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun EmptyListScreen(
    modifier: Modifier = Modifier,
    message: String
) {
    Box(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = message,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}