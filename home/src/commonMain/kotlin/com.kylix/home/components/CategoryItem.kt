package com.kylix.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
        backgroundColor = Neutral200
    ) {
        Text(
             modifier = Modifier.padding(8.dp),
            text = category.name,
            style = MaterialTheme.typography.body2,
        )
    }
}