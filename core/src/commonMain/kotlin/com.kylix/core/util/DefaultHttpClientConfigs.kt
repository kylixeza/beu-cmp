package com.kylix.core.util

import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.bearerAuth
import io.ktor.http.ContentType
import io.ktor.http.contentLength
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun<T : HttpClientEngineConfig> HttpClientConfig<T>.beuDefaultRequest(
    token: String,
    block: DefaultRequest.DefaultRequestBuilder.() -> Unit = {}
) {
    defaultRequest {
        url("https://beu-api.up.railway.app/")
        bearerAuth(token)
        contentType(ContentType.Application.Json)
        block()
    }
}

fun<T : HttpClientEngineConfig> HttpClientConfig<T>.beuDefaultLogging(
    block: Logging.Config.() -> Unit = {}
) {
    install(Logging) {
        level = LogLevel.ALL
        logger = Logger.DEFAULT
        block()
    }
}

fun<T: HttpClientEngineConfig> HttpClientConfig<T>.beuDefaultContentNegotiation(
    block: ContentNegotiation.Config.() -> Unit = {}
) {
    install(ContentNegotiation) {
        json(
            json = Json {
                ignoreUnknownKeys = true
                encodeDefaults = true
            }
        )
        block()
    }
}

fun <T: HttpClientEngineConfig> HttpClientConfig<T>.beuDefaultRetries() {
    install(HttpRequestRetry) {
        retryOnExceptionOrServerErrors(maxRetries = 10)
        exponentialDelay()
    }
}