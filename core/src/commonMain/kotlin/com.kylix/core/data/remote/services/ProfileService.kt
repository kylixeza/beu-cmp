package com.kylix.core.data.remote.services

import io.ktor.client.HttpClient
import io.ktor.client.request.get

class ProfileService(
    private val httpClient: HttpClient
) {

    suspend fun greet() = httpClient.get("profile/greet")
}