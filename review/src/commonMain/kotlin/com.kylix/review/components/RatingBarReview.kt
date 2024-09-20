package com.kylix.review.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import beukmm.common.generated.resources.Res
import beukmm.common.generated.resources.ic_star
import beukmm.common.generated.resources.ic_unstar
import org.jetbrains.compose.resources.painterResource

@Composable
fun RatingBarReview(
    modifier: Modifier = Modifier,
    maxRating: Int = 5,
    onRatingChanged: (Int) -> Unit = {},
) {
    var rating by remember { mutableStateOf(0) }
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        for (i in 1..maxRating) {
            var key by remember { mutableStateOf(0) }
            val rotation by animateFloatAsState(
                targetValue = if (i <= rating) 360f else 0f,
                animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
            )
            val bounce by animateFloatAsState(
                targetValue = if (i <= rating) 1f else 0f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )

            Image(
                painter = painterResource(
                    if (i <= rating) Res.drawable.ic_star else Res.drawable.ic_unstar
                ),
                contentDescription = "Star $i",
                modifier = Modifier
                    .weight(1f)
                    .size(40.dp)
                    .rotate(rotation)
                    .graphicsLayer(
                        scaleX = 1f + (bounce * 0.2f),
                        scaleY = 1f + (bounce * 0.2f),
                    )
                    .clickable {
                        rating = i
                        onRatingChanged(i)
                        key++ // Trigger recomposition for animation
                    }
            )
        }
    }
}