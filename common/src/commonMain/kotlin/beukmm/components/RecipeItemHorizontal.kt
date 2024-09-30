package beukmm.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import beukmm.common.generated.resources.Res
import beukmm.common.generated.resources.ic_estimated_time
import beukmm.common.generated.resources.ic_exclusive_tag
import beukmm.common.generated.resources.ic_favorite
import beukmm.common.generated.resources.ic_star
import beukmm.theme.Error50
import beukmm.theme.Error500
import beukmm.theme.Error900
import beukmm.theme.Neutral300
import beukmm.theme.Primary50
import beukmm.theme.Primary700
import beukmm.theme.Primary900
import beukmm.theme.Success50
import beukmm.theme.Success900
import beukmm.theme.White
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun RecipeItemHorizontal(
    modifier: Modifier = Modifier,
    imageUrl: String,
    isExclusive: Boolean,
    difficulty: String,
    foodName: String,
    favoritesCount: Long,
    rating: Double,
    cookTime: Int,
    onItemClick: () -> Unit = {}
) {

    val cardDifficultyColor = when (difficulty) {
        "Mudah" -> Success50
        "Menengah" -> Primary50
        "Sulit" -> Error50
        else -> Primary50
    }

    val difficultyColor = when (difficulty) {
        "Mudah" -> Success900
        "Menengah" -> Primary900
        "Sulit" -> Error900
        else -> Primary900
    }

    val painterResources = asyncPainterResource(data = imageUrl)

    Card(
        modifier = modifier
            .width(IntrinsicSize.Min)
            .padding(end = 20.dp, top = 6.dp, bottom = 6.dp)
            .clickable { onItemClick() },
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp)
    ) {
        Column(modifier = Modifier.padding(bottom = 12.dp)) {
            Box {
                KamelImage (
                    resource = painterResources,
                    contentDescription = "Food Image",
                    modifier = Modifier
                        .size(width = 180.dp, height = 120.dp),
                    contentScale = ContentScale.Crop
                )
                if (isExclusive) {
                    Image(
                        painter = painterResource(Res.drawable.ic_exclusive_tag),
                        contentDescription = "Exclusive",
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(8.dp)
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = foodName,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Card(
                    shape = RoundedCornerShape(4.dp),
                    colors = CardDefaults.cardColors(containerColor = cardDifficultyColor),
                ) {
                    Text(
                        text = difficulty,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 8.sp,
                            color = difficultyColor
                        ),
                        fontSize = 8.sp,
                        modifier = Modifier.padding(horizontal = 2.dp, vertical = 2.dp)
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_favorite),
                    contentDescription = "Favorite",
                    modifier = Modifier.size(10.dp),
                    tint = Error500
                )
                Text(
                    text = "$favoritesCount other users",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 8.sp
                    ),
                    modifier = Modifier.padding(start = 4.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_star),
                        contentDescription = "Rating",
                        modifier = Modifier.size(12.dp),
                        tint = Primary700
                    )
                    Text(
                        modifier = Modifier.padding(start = 2.dp),
                        text = rating.toString(),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 10.sp
                        ),
                        color = Primary700,
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_estimated_time),
                        contentDescription = "Cook Time",
                        modifier = Modifier.size(12.dp),
                        tint = Neutral300
                    )
                    Text(
                        text = "$cookTime min",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 10.sp
                        ),
                        color = Neutral300,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
        }
    }
}