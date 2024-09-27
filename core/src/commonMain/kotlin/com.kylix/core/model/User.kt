package com.kylix.core.model

data class User(
    val username: String,
    val name: String,
    val avatar: String,
    val phoneNumber: String,
    val email: String?
)