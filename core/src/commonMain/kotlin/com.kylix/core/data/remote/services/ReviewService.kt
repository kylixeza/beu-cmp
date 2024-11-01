package com.kylix.core.data.remote.services

import io.ktor.client.HttpClient
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.request.get
import io.ktor.http.Headers

class ReviewService(
    private val client: HttpClient
) {

    suspend fun postReview(
        historyId: String,
        jsonBody: String,
        images: List<ByteArray>
    ) = client.submitFormWithBinaryData(
        url = "histories/$historyId/review",
        formData = formData {
            append("body", jsonBody, Headers.build {
                append("Content-Type", "application/json")
            })

            images.forEachIndexed { index, image ->
                append("image", image, Headers.build {
                    append("Content-Type", "image/jpeg")
                    append("Content-Disposition", "form-data; name=\"images\"; filename=\"image$index.jpg\"")
                })
            }
        }
    )

    suspend fun getReview(historyId: String) = client.get("histories/$historyId/review")

}