package beukmm.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import beukmm.theme.Secondary500
import beukmm.theme.White

@Composable
fun LoadingDialog(
    onDismissRequest: () -> Unit = {}
) {
    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        Card(
            modifier = Modifier,
            elevation = 8.dp,
            backgroundColor = White,
            shape = RoundedCornerShape(8.dp)
        ) {
            CircularProgressIndicator(
                modifier = Modifier.padding(24.dp).size(54.dp),
                color = Secondary500,
                strokeWidth = 6.dp
            )
        }
    }
}