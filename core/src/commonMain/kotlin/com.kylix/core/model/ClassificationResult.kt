package com.kylix.core.model

data class ClassificationResult(
    val classifiedImage: String,
    val isFood: Boolean,
    val relatedRecipes: List<RecipeList>,
)
