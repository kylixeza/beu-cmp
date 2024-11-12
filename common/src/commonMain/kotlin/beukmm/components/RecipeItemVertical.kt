package beukmm.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import beukmm.util.cardDifficultyColor
import beukmm.util.textDifficultyColor
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun RecipeItemVertical(
    modifier: Modifier = Modifier,
    imageUrl: String,
    difficulty: String,
    foodName: String,
    favoritesCount: Long,
    rating: Double,
    cookTime: Int,
    onItemClick: () -> Unit = {}
) {

    val painterResources = asyncPainterResource(data = imageUrl)

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onItemClick() },
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min)
        ) {

            KamelImage (
                resource = painterResources,
                contentDescription = "Food Image",
                modifier = Modifier
                    .size(112.dp)
                    .fillMaxHeight(),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = foodName,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Card(
                    shape = RoundedCornerShape(4.dp),
                    colors = CardDefaults.cardColors(containerColor = difficulty.cardDifficultyColor()),
                ) {
                    Text(
                        text = difficulty,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 8.sp,
                            color = difficulty.textDifficultyColor()
                        ),
                        fontSize = 8.sp,
                        modifier = Modifier.padding(horizontal = 2.dp, vertical = 2.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
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

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
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

                    Spacer(modifier = Modifier.width(4.dp))

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
}