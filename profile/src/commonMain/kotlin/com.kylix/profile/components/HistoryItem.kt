package com.kylix.profile.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import beukmm.common.generated.resources.Res
import beukmm.common.generated.resources.ic_estimated_time
import beukmm.common.generated.resources.ic_star
import beukmm.common.generated.resources.ic_unstar
import beukmm.theme.Neutral300
import beukmm.theme.Neutral400
import beukmm.theme.Secondary500
import beukmm.theme.White
import beukmm.util.customKamelConfig
import com.kylix.core.model.History
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import io.kamel.image.config.LocalKamelConfig
import org.jetbrains.compose.resources.painterResource

@Composable
fun HistoryItem(
    history: History,
    onGoToReviewPage: () -> Unit = {},
    onShowReview: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            CompositionLocalProvider(LocalKamelConfig provides customKamelConfig) {
                val painter = asyncPainterResource(history.recipeImage)
                KamelImage(
                    resource = painter,
                    contentDescription = null,
                    modifier = Modifier
                        .size(120.dp, 90.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = history.recipeName,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontSize = 12.sp
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = history.timeStamp,
                    style = MaterialTheme.typography.bodySmall,
                )

                Spacer(modifier = Modifier.height(6.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_estimated_time),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = Secondary500
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = history.spendTime,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 10.sp,
                            color = Secondary500
                        ),
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        ReviewCard(
            modifier = Modifier.padding(horizontal = 24.dp),
            history = history,
            onReviewClick = {
                if (history.isReviewed) onShowReview()
                else onGoToReviewPage()
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        HorizontalDivider(color = Neutral400, thickness = 1.dp)
    }
}

@Composable
private fun ReviewCard(
    modifier: Modifier = Modifier,
    history: History,
    onReviewClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onReviewClick() },
        colors = CardDefaults.cardColors(containerColor = White),
        border = BorderStroke(1.dp, Neutral300),
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Review",
                style = MaterialTheme.typography.bodyMedium,
            )

            RatingBar(
                rating = history.reviewRating
            )
        }
    }
}

@Composable
private fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Int
) {

    val stars by remember { mutableStateOf(rating) }
    val unstars by remember { mutableStateOf(5 - rating) }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 0 until stars) {
            Image(
                painter = painterResource(Res.drawable.ic_star),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
        }
        for (i in 0 until unstars) {
            Image(
                painter = painterResource(Res.drawable.ic_unstar),
                contentDescription = null,
                modifier = Modifier.size(14.dp)
            )
        }
    }
}