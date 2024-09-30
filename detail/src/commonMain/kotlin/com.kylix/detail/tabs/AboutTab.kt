package com.kylix.detail.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import beukmm.common.generated.resources.Res
import beukmm.common.generated.resources.ic_circle_soft_orange
import beukmm.common.generated.resources.ic_star
import beukmm.theme.Primary300
import beukmm.theme.Primary500
import beukmm.theme.Primary700
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.kylix.detail.DetailState
import org.jetbrains.compose.resources.painterResource

class AboutTab(
    private val detailState: DetailState
) : Tab {

    @Composable
    override fun Content() {
        RecipeDetailsScreen()
    }

    @Composable
    private fun RecipeDetailsScreen() {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp)
        ) {
            item {
                ReviewSummary()
                Spacer(modifier = Modifier.height(18.dp))
            }

            item {
                Description()
                Spacer(modifier = Modifier.height(18.dp))
            }

            item {
                EstimateTime()
                Spacer(modifier = Modifier.height(18.dp))
            }

            item {
                Text(
                    text = "Nutrition Information",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontSize = 14.sp
                    )
                )
                Spacer(modifier = Modifier.height(6.dp))
            }

            items(detailState.recipe?.nutritionInformation.orEmpty()) {
                NutritionItem(
                    nutritionName = it.name,
                    nutritionAmount = it.amount
                )
            }
        }
    }

    @Composable
    private fun ReviewSummary() {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Review Summary",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontSize = 14.sp
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Reviewed ${detailState.recipe?.reviews?.size} time${detailState.recipe?.reviews?.size?.let { if (it > 1) "s" else "" }}",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 10.sp,
                    )
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(Res.drawable.ic_star),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = detailState.recipe?.averageRating.toString(),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 16.sp,
                        color = Primary700
                    )
                )
            }
        }
    }

    @Composable
    private fun Description() {
        Column {
            Text(
                text = "Description",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontSize = 14.sp
                )
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = detailState.recipe?.description.orEmpty(),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 12.sp
                )
            )
        }
    }

    @Composable
    private fun EstimateTime() {
        Column {
            Text(
                text = "Estimate Time",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontSize = 14.sp
                )
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "${detailState.recipe?.estimateTime}",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 12.sp
                )
            )
        }
    }

    @Composable
    private fun NutritionItem(
        nutritionName: String,
        nutritionAmount: String,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_circle_soft_orange),
                contentDescription = null,
                tint = Primary300,
                modifier = Modifier.size(16.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = nutritionName,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 14.sp
                ),
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = nutritionAmount,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 12.sp,
                    color = Primary500
                )
            )
        }
    }

    override val options: TabOptions
        @Composable
        get() = TabOptions(
            index = 0u,
            title = "About",
            icon = null
        )
}