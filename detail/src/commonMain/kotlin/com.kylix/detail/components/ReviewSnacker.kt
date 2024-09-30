package com.kylix.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
        colors = CardDefaults.cardColors(containerColor = Primary900),
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
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = White,
                        fontSize = 14.sp
                    )
                )
                Text(
                    text = "You've been cooking for $cookingSessionDuration",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = White,
                        fontSize = 12.sp
                    )
                )
            }
            
            Button(
                onClick = onReviewNow,
                colors = ButtonDefaults.buttonColors(containerColor = White),
                modifier = Modifier.wrapContentSize()
            ) {
                Text(
                    text = "Review Now",
                    color = Primary900,
                    style = MaterialTheme.typography.titleMedium.copy(fontSize = 12.sp)
                )
            }
        }
    }
}