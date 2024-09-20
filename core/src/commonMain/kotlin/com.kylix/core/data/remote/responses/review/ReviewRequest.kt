package com.kylix.core.data.remote.responses.review

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewRequest(
    @SerialName("rating")
    val rating: Int,
    @SerialName("comment")
    val comment: String,
)
