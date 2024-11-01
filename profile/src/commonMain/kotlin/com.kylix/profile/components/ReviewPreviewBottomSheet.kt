package com.kylix.profile.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
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
import androidx.compose.ui.unit.dp
import beukmm.common.generated.resources.Res
import beukmm.common.generated.resources.ic_star
import beukmm.common.generated.resources.ic_unstar
import beukmm.common.generated.resources.ilu_default_profile_picture
import beukmm.components.NetworkFlexboxImages
import beukmm.theme.Neutral300
import beukmm.theme.Neutral500
import beukmm.theme.Primary500
import beukmm.theme.White
import beukmm.util.customKamelConfig
import com.kylix.core.model.Review
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import io.kamel.image.config.LocalKamelConfig
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewPreviewBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    review: Review,
    onClose: () -> Unit
) {

    ModalBottomSheet(
        modifier = modifier,
        containerColor = White,
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        onDismissRequest = onClose
    ) {
        Column(
            modifier = modifier.padding(
                horizontal = 24.dp,
                vertical = 16.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                CompositionLocalProvider(LocalKamelConfig provides customKamelConfig) {
                    KamelImage(
                        asyncPainterResource(review.avatar),
                        contentDescription = "Reviewer image",
                        modifier = Modifier.size(40.dp).clip(CircleShape),
                        contentScale = ContentScale.Crop,
                        onFailure = {
                            Image(
                                painter = painterResource(Res.drawable.ilu_default_profile_picture),
                                contentDescription = "Reviewer image",
                                modifier = Modifier.size(40.dp).clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                        }
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Column(
                    modifier = Modifier.weight(1f).fillMaxWidth()
                ) {
                    Text(
                        text = review.username,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = review.timeStamp,
                        style = MaterialTheme.typography.bodySmall.copy(color = Neutral500)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 0.dp),
                colors = CardDefaults.cardColors(containerColor = White),
                border = BorderStroke(1.dp, Neutral300)
            ) {
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Review",
                            style = MaterialTheme.typography.titleMedium
                        )
                        RatingBar(
                            rating = review.rating,
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = review.comment,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    NetworkFlexboxImages(
                        images = review.images
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onClose,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Primary500)
            ) {
                Text(
                    text = "Close",
                    style = MaterialTheme.typography.bodyMedium.copy(color = White)
                )
            }
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
                modifier = Modifier.size(24.dp)
            )
        }
        for (i in 0 until unstars) {
            Image(
                painter = painterResource(Res.drawable.ic_unstar),
                contentDescription = null,
                modifier = Modifier.size(22.dp)
            )
        }
    }
}