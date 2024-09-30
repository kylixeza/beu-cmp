package com.kylix.onboard.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import beukmm.theme.Primary500
import beukmm.theme.White

@Composable
fun FinishButton(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    count: Int,
    onClick : () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            modifier = Modifier,
            visible = pagerState.currentPage == count - 1,
            enter = slideInHorizontally(
                initialOffsetX = { -it },
            ),
            exit = slideOutHorizontally(
                targetOffsetX = { (-it * 1.2).toInt() },
            )
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onClick,
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Primary500
                ),
                contentPadding = PaddingValues(8.dp)
            ) {
                Text(
                    text = "Finish",
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = White
                    ),
                )
            }
        }
    }
}