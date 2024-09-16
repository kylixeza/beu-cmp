package com.kylix.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import beukmm.theme.Primary900
import beukmm.theme.White
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.CloseCircle

@Composable
fun ReviewSnacker(
    cookingSessionDuration: String,
    onCloseReview: () -> Unit = {},
    onReviewNow: () -> Unit = {}
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
        backgroundColor = Primary900,
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                modifier = Modifier.size(24.dp),
                onClick = onCloseReview
            ) {
                Icon(
                    imageVector = EvaIcons.Fill.CloseCircle,
                    contentDescription = "Close",
                    tint = White
                )
            }
            
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp)
            ) {
                Text(
                    text = "Let's give review!",
                    style = MaterialTheme.typography.h5.copy(
                        color = White,
                        fontSize = 14.sp
                    )
                )
                Text(
                    text = "You've been cooking for $cookingSessionDuration",
                    style = MaterialTheme.typography.body2.copy(
                        color = White,
                        fontSize = 12.sp
                    )
                )
            }
            
            Button(
                onClick = onReviewNow,
                colors = ButtonDefaults.buttonColors(backgroundColor = White),
                modifier = Modifier.wrapContentSize()
            ) {
                Text(
                    text = "Review Now",
                    color = Primary900,
                    style = MaterialTheme.typography.h5.copy(
                        fontSize = 12.sp
                    )
                )
            }
        }
    }
}