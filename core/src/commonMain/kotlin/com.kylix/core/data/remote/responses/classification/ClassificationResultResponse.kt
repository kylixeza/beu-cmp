package com.kylix.core.data.remote.responses.classification

import com.kylix.core.data.remote.responses.recipe.RecipeListResponse
import com.kylix.core.model.ClassificationResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ClassificationResultResponse(
    @SerialName("classified_image")
    val classifiedImage: String,

    @SerialName("is_food")
    val isFood: Boolean,

    @SerialName("related_recipes")
    val relatedRecipes: List<RecipeListResponse>
) {
    fun toClassificationResult() = ClassificationResult(
        classifiedImage = classifiedImage,
        isFood = isFood,
        relatedRecipes = relatedRecipes.map { it.toRecipeList() }
    )
}
