
package com.kylix.core.model

data class RecipeDetail(
    val recipeId: String,
    val isFavorite: Boolean,
    val name: String,
    val video: String,
    val videoSrc: String,
    val ingredients: List<String>,
    val tools: List<String>,
    val steps: List<String>,
    val averageRating: Double,
    val averageCount: Long,
    val description: String,
    val estimateTime: String,
    val commentsCount: Int,
    val nutritionInformation: List<Nutrition>,
    val reviews: List<Review>
)