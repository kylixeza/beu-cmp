package com.kylix.core.data.remote.responses.review

import com.kylix.core.model.Review
import kotlinx.serialization.Serializable

@Serializable
data class ReviewResponse(
    val reviewId: String,
    val username: String,
    val avatar: String,
    val rating: Int,
    val comment: String,
    val timeStamp: String,
    val images: List<String>
) {
    fun toReview() = Review(
        reviewId = reviewId,
        username = username,
        avatar = avatar,
        rating = rating,
        comment = comment,
        timeStamp = timeStamp,
        images = images
    )
}
