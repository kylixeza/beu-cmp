package com.kylix.review.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import beukmm.theme.Primary500

@Composable
fun DottedBorderRectangle(
    modifier: Modifier = Modifier,
    onBoxPressed: () -> Unit = {},
    content : @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .height(200.dp)
            .background(Color.Transparent)
            .clickable {
                onBoxPressed()
            }
    ) {
        Canvas(
            modifier = Modifier
                .matchParentSize()
        ) {
            drawRoundRect(
                color = Primary500,
                style = Stroke(
                    width = 4.dp.toPx(),
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10.dp.toPx(), 10.dp.toPx()))
                )
            )
        }
        content()
    }
}