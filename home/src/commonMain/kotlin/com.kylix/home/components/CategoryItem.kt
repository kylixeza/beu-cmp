package com.kylix.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import beukmm.theme.Neutral200
import com.kylix.core.model.Category

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    category: Category,
    onCategorySelected: (String, String) -> Unit = {_, _ -> }
) {
    Card(
        modifier = modifier
            .padding(end = 8.dp)
            .clickable { onCategorySelected(category.categoryId, category.name) },
        colors = CardDefaults.cardColors(
            containerColor = Neutral200
        )
    ) {
        Text(
             modifier = Modifier.padding(8.dp),
            text = category.name,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}