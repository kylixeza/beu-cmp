package com.kylix.core.data.remote.responses.nutrition

import com.kylix.core.model.Nutrition
import kotlinx.serialization.Serializable

@Serializable
data class NutritionResponse(
    val nutritionId: String,
    val name: String,
    val amount: String
) {
    fun toNutrition() = Nutrition(
        nutritionId = nutritionId,
        name = name,
        amount = amount
    )
}
