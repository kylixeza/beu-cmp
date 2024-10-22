package com.kylix.core.data.remote.services

import com.kylix.core.data.remote.requests.LoginRequest
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class AuthService(
    private val client: HttpClient,
) {

    suspend fun login(
        body: LoginRequest
    ) = client.post("login") {
        setBody(body)
    }

    suspend fun logout() = client.post("logout")

}