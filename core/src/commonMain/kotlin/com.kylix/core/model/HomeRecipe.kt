package com.kylix.core.model

data class HomeRecipe(
    val title: String,
    val subtitle: String?,
    val recipes: List<RecipeList>
)