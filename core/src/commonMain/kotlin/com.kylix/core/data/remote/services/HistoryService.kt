package com.kylix.core.data.remote.services

import com.kylix.core.data.remote.requests.HistoryRequest
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class HistoryService(
    private val client: HttpClient
) {

    suspend fun postHistory(
        body: HistoryRequest
    ) = client.post("histories") {
        setBody(body)
    }

}