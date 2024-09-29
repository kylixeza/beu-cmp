package com.kylix.core.data.remote.services

import io.ktor.client.HttpClient
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.request.get
import io.ktor.client.request.put
import io.ktor.http.HttpMethod

class ProfileService(
    private val httpClient: HttpClient
) {

    suspend fun greet() = httpClient.get("profile/greet")

    suspend fun getProfile() = httpClient.get("profile")

    suspend fun updateProfile(
        jsonBody: String,
        avatar: ByteArray?
    ) = httpClient.submitFormWithBinaryData(
        url = "profile",
        formData = formData {
            append("body", jsonBody)
            if (avatar != null) {
                append("image", avatar)
            }
        }
    ) {
        this.method = HttpMethod.Put
    }
}