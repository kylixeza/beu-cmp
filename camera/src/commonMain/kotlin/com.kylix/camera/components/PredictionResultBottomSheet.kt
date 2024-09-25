package com.kylix.camera.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import beukmm.components.RecipeItemVertical
import beukmm.theme.Primary500
import beukmm.theme.White
import com.kylix.core.model.RecipeList

@Composable
fun PredictionResultBottomSheet(
    modifier: Modifier = Modifier,
    result: String,
    recipes: List<RecipeList>,
    sheetState: ModalBottomSheetState,
    onItemSelected: (String) -> Unit = {},
    content: @Composable () -> Unit
) {

    ModalBottomSheetLayout(
        sheetBackgroundColor = White,
        sheetState = sheetState,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetContent = {
            Row(
                modifier = modifier.padding(horizontal = 24.dp, vertical = 12.dp)
            ) {
                Text(
                    "Result: ",
                    style = MaterialTheme.typography.body2
                )
                Text(
                    result,
                    style = MaterialTheme.typography.body2.copy(
                        color = Primary500,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            LazyColumn(
                contentPadding = PaddingValues(
                    start = 24.dp,
                    end = 24.dp,
                    top = 12.dp,
                    bottom = 92.dp
                )
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
    ) {
        content()
    }

}