package com.kylix.core.data.remote.responses.recipe

import com.kylix.core.model.HomeRecipe
import kotlinx.serialization.Serializable

@Serializable
data class HomeRecipeResponse(
    val title: String,
    val subtitle: String? = null,
    val recipes: List<RecipeListResponse>
) {
    fun toHomeRecipe() = HomeRecipe(
        title = title,
        subtitle = subtitle,
        recipes = recipes.map { it.toRecipeList() }
    )
}
