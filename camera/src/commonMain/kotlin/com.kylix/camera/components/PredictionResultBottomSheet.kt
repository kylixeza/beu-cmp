package com.kylix.camera.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import beukmm.components.RecipeItemVertical
import beukmm.theme.Primary500
import beukmm.theme.White
import com.kylix.core.model.RecipeList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PredictionResultBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    result: String,
    recipes: List<RecipeList>,
    onItemSelected: (String) -> Unit = {},
    onDismissRequest: () -> Unit = {},
) {

    val insets = WindowInsets.navigationBars.asPaddingValues()

    ModalBottomSheet(
        modifier = modifier,
        containerColor = White,
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        onDismissRequest = onDismissRequest,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
        ) {
            Text(
                "Result: ",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                result,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Primary500,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        LazyColumn(
            modifier = Modifier,
            contentPadding = PaddingValues(
                start = 24.dp,
                end = 24.dp,
                top = 12.dp,
                bottom = insets.calculateBottomPadding()
            ),
        ) {
            items(recipes) { recipe ->
                RecipeItemVertical(
                    imageUrl = recipe.image,
                    difficulty = recipe.difficulty,
                    foodName = recipe.name,
                    favoritesCount = recipe.favorites,
                    rating = recipe.rating,
                    cookTime = recipe.estimationTime,
                    onItemClick = { onItemSelected(recipe.recipeId) }
                )
            }
        }
    }
}