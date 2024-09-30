package com.kylix.detail.tabs

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import beukmm.common.generated.resources.Res
import beukmm.common.generated.resources.ic_circle_soft_orange
import beukmm.theme.Primary300
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.kylix.detail.DetailState
import org.jetbrains.compose.resources.painterResource

class InstructionTab(
    private val detailState: DetailState
) : Tab {

    @Composable
    override fun Content() {
        val column by remember { mutableStateOf(2) }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp)
        ) {
            item {
                Text(
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = "Ingredients",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontSize = 14.sp
                    )
                )
            }

            items(detailState.recipe?.ingredients.orEmpty().chunked(column)) {
                Row(
                    modifier = Modifier.padding(vertical = 2.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    for ((index, item) in it.withIndex()) {
                        Row(
                            modifier = Modifier.fillMaxWidth(1f / (column - index))
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.ic_circle_soft_orange),
                                contentDescription = null,
                                tint = Primary300,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = item,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontSize = 12.sp
                                ),
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = "Tools",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontSize = 14.sp
                    )
                )
            }

            items(detailState.recipe?.tools.orEmpty().chunked(column)) {
                Row(
                    modifier = Modifier.padding(vertical = 2.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    for ((index, item) in it.withIndex()) {
                        Row(
                            modifier = Modifier.fillMaxWidth(1f / (column - index))
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.ic_circle_soft_orange),
                                contentDescription = null,
                                tint = Primary300,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = item,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontSize = 12.sp
                                ),
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = "Steps",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontSize = 14.sp
                    )
                )
            }

            itemsIndexed(detailState.recipe?.steps.orEmpty()) { index, item ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                ) {
                    Text(
                        text = "${index + 1}.",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 12.sp
                        ),
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = item,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 12.sp
                        ),
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }

    override val options: TabOptions
        @Composable
        get() = TabOptions(
            index = 1u,
            title = "Instruction",
            icon = null
        )
}