package com.kylix.core.data.remote.responses.history

import com.kylix.core.model.History
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HistoryResponse(
    @SerialName("history_id")
    val historyId: String,
    @SerialName("recipe_name")
    val recipeName: String,
    @SerialName("recipe_image")
    val recipeImage: String,
    @SerialName("time_stamp")
    val timeStamp: String,
    @SerialName("spend_time")
    val spendTime: String,
    @SerialName("is_reviewed")
    val isReviewed: Boolean,
    @SerialName("review_rating")
    val reviewRating: Int,
) {
    fun toHistory() = History(
        historyId = historyId,
        recipeName = recipeName,
        recipeImage = recipeImage,
        timeStamp = timeStamp,
        spendTime = spendTime,
        isReviewed = isReviewed,
        reviewRating = reviewRating,
    )
}