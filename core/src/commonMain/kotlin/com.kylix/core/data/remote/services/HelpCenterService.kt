package com.kylix.core.data.remote.services

import com.kylix.core.data.remote.requests.HelpCenterRequest
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class HelpCenterService(
    private val client: HttpClient
) {

    suspend fun postMessage(body: HelpCenterRequest) = client.post("help-center") {
        setBody(body)
    }
}