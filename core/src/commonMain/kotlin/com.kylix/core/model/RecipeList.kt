package com.kylix.core.model

data class RecipeList(
    val recipeId: String,
    val name: String,
    val difficulty: String,
    val image: String,
    val favorites: Long,
    val isFavorite: Boolean,
    val rating: Double,
    val estimationTime: Int,
)