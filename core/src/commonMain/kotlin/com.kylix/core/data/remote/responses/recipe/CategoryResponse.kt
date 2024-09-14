package com.kylix.core.data.remote.responses.recipe

import com.kylix.core.model.Category
import kotlinx.serialization.Serializable

@Serializable
data class CategoryResponse(
    val categoryId: String,
    val name: String,
) {
    fun toCategory() = Category(
        categoryId = categoryId,
        name = name
    )
}