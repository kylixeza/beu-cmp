package com.kylix.core.model

data class Review(
    val reviewId: String,
    val username: String,
    val avatar: String,
    val rating: Int,
    val comment: String,
    val timeStamp: String,
    val images: List<String>
)
