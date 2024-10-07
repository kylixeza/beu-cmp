package com.kylix.core.data.remote.requests

import kotlinx.serialization.Serializable

@Serializable
data class PasswordRequest(
    val password: String
)
