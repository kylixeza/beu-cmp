package com.kylix.detail.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import beukmm.common.generated.resources.Res
import beukmm.common.generated.resources.ic_star
import beukmm.common.generated.resources.ic_unstar
import beukmm.common.generated.resources.ilu_default_profile_picture
import beukmm.theme.Primary700
import beukmm.util.customKamelConfig
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.kylix.detail.DetailState
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import io.kamel.image.config.LocalKamelConfig
import org.jetbrains.compose.resources.painterResource

class ReviewTab(
    private val detailState: DetailState
) : Tab {

    @Composable
    override fun Content() {

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(
                detailState.recipe?.reviews.orEmpty(),
                key = { it.reviewId }
            ) {
                ReviewItem(
                    avatar = it.avatar,
                    reviewer = it.username,
                    rating = it.rating,
                    review = it.comment,
                    reviewImages = it.images
                )
            }
        }
    }

    @Composable
    private fun ReviewItem(
        avatar: String,
        reviewer: String,
        rating: Int,
        review: String,
        reviewImages: List<String>
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CompositionLocalProvider(LocalKamelConfig provides customKamelConfig) {
                    KamelImage(
                        asyncPainterResource(avatar),
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

                Text(
                    text = reviewer,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.weight(1f)
                )

                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RatingBar(
                        rating = rating
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = rating.toString(),
                        style = MaterialTheme.typography.h5.copy(
                            color = Primary700
                        ),
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = review,
                style = MaterialTheme.typography.body2.copy(fontSize = 12.sp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            ReviewImages(
                images = reviewImages
            )
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

    @Composable
    private fun ReviewImages(
        images: List<String>
    ) {
        val density = LocalDensity.current

        var rowWidthDp by remember { mutableStateOf(0.dp) }
        val itemSpacing by remember { mutableStateOf(8.dp) }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    rowWidthDp = with(density) { coordinates.size.width.toDp() }
                },
            horizontalArrangement = Arrangement.spacedBy(itemSpacing)
        ) {
            val itemSize = (rowWidthDp - (itemSpacing * 2)) / 3
            images.forEach {
                Card(
                    modifier = Modifier
                        .size(itemSize)
                        .aspectRatio(1f),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    CompositionLocalProvider(LocalKamelConfig provides customKamelConfig) {
                        KamelImage(
                            asyncPainterResource(it),
                            contentDescription = "Review image",
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
    }


    override val options: TabOptions
        @Composable
        get() = TabOptions(
            index = 2u,
            title = "Review",
            icon = null
        )
}