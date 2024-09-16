package com.kylix.core.data.remote.responses.recipe

import com.kylix.core.data.remote.responses.nutrition.NutritionResponse
import com.kylix.core.data.remote.responses.review.ReviewResponse
import com.kylix.core.model.RecipeDetail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeDetailResponse(
    @SerialName("recipe_id")
    val recipeId: String,
    @SerialName("is_favorite")
    val isFavorite: Boolean,
    val name: String,
    val video: String,
    @SerialName("video_src")
    val videoSrc: String,
    val ingredients: List<String>,
    val tools: List<String>,
    val steps: List<String>,
    @SerialName("average_rating")
    val averageRating: Double,
    @SerialName("average_count")
    val averageCount: Long,
    val description: String,
    @SerialName("estimate_time")
    val estimateTime: String,
    @SerialName("comments_count")
    val commentsCount: Int,
    @SerialName("nutrition_information")
    val nutritionInformation: List<NutritionResponse>,
    val reviews: List<ReviewResponse>
) {
    fun toRecipeDetail() = RecipeDetail(
        recipeId = recipeId,
        isFavorite = isFavorite,
        name = name,
        video = video,
        videoSrc = videoSrc,
        ingredients = ingredients,
        tools = tools,
        steps = steps,
        averageRating = averageRating,
        averageCount = averageCount,
        description = description,
        estimateTime = estimateTime,
        commentsCount = commentsCount,
        nutritionInformation = nutritionInformation.map { it.toNutrition() },
        reviews = reviews.map { it.toReview() }
    )
}
