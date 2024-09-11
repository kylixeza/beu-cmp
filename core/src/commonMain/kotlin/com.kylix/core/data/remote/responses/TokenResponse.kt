package com.kylix.core.data.remote.responses

import kotlinx.serialization.Serializable

@Serializable
data class TokenResponse(
    val token: String
)