package com.kylix.onboard.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerIndicator(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    count: Int,
    selectedColor: Color = MaterialTheme.colors.secondary,
    unselectedColor: Color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f),
    selectedLength : Dp = 24.dp,
    size: Dp = 12.dp,
    dotSpace: Dp = 12.dp
) {

    var currentPage by remember { mutableStateOf(0) }

    LaunchedEffect(key1 = pagerState.currentPage) {
        currentPage = pagerState.currentPage
    }

    Row(
        modifier = modifier.fillMaxWidth().padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(count) { index ->
            val isSelected = index == currentPage
            PagerDot(
                isSelected = isSelected,
                selectedColor = selectedColor,
                unselectedColor = unselectedColor,
                selectedLength = selectedLength,
                size = size,
                dotSpace = dotSpace
            )
        }
    }
}

@Composable
private fun PagerDot(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    selectedColor: Color,
    unselectedColor: Color,
    selectedLength: Dp,
    size: Dp,
    dotSpace: Dp
) {

    val color: Color by animateColorAsState(
        targetValue = if (isSelected) selectedColor else unselectedColor,
        animationSpec = tween(durationMillis = 200)
    )
    val width: Dp by animateDpAsState(
        targetValue = if (isSelected) selectedLength else size,
        animationSpec = tween(durationMillis = 200)
    )

    Box(
        modifier = modifier
            .padding(horizontal = dotSpace / 2)
            .size(width = width, height = size)
            .clip(CircleShape)
            .background(color)
    )
}