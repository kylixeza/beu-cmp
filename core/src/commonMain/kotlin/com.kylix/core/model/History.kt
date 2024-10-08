
package com.kylix.core.model

data class History(
    val historyId: String,
    val recipeName: String,
    val recipeImage: String,
    val timeStamp: String,
    val spendTime: String,
    val isReviewed: Boolean,
    val reviewRating: Int,
)