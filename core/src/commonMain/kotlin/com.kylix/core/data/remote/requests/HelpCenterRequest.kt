package com.kylix.core.data.remote.requests

import kotlinx.serialization.Serializable

@Serializable
data class HelpCenterRequest(
    private val message: String
)