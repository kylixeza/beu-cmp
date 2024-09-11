package com.kylix.core.data.remote.requests

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val identifier: String,
    val password: String
)