package com.kylix.core.data.remote.requests

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HistoryRequest(
    @SerialName("recipe_id")
    val recipeId: String,
    @SerialName("spend_time")
    val timeSpent: Int,
)