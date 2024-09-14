package com.kylix.core.data.remote.responses.recipe

import com.kylix.core.model.RecipeList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeListResponse(
    @SerialName("recipe_id")
    val recipeId: String,
    val name: String,
    val difficulty: String,
    val image: String,
    val favorites: Long,
    val isFavorite: Boolean,
    val rating: Double,
    @SerialName("estimation_time")
    val estimationTime: Int,
) {
    fun toRecipeList() = RecipeList(
        recipeId = recipeId,
        name = name,
        difficulty = difficulty,
        image = image,
        favorites = favorites,
        isFavorite = isFavorite,
        rating = rating,
        estimationTime = estimationTime
    )
}
