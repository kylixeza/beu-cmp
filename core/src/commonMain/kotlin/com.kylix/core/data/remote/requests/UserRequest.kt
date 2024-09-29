package com.kylix.core.data.remote.requests

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserRequest(
    val username: String,
    val name: String,
    @SerialName("phone_number")
    val phoneNumber: String,
    val email: String?
)