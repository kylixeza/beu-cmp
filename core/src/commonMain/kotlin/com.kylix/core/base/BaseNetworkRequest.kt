package com.kylix.core.base

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.kylix.core.data.remote.responses.BaseResponse
import com.kylix.core.util.Error
import com.kylix.core.util.Success
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.Json

abstract class BaseNetworkRequest<ResultType, ResponseType> {

    suspend fun run(): Result<Success<ResultType>, Error> {
        preRequest()
        return try {
            val httpResponse = createCall()
            val responseJson = httpResponse.bodyAsText()
            val baseResponseOf = Json.decodeFromString(deserialize(responseJson), responseJson)

            if (httpResponse.status.value in 200..299) {
                saveCallResult(baseResponseOf.data ?: return Err(Error("Data not found")))
                val domainData = baseResponseOf.data.mapResponse()
                Ok(Success(domainData))
            } else {
                Err(Error(baseResponseOf.message))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    protected open suspend fun preRequest() {}

    protected abstract suspend fun createCall(): HttpResponse

    protected abstract suspend fun ResponseType.mapResponse(): ResultType

    protected abstract fun deserialize(responseJson: String): DeserializationStrategy<BaseResponse<ResponseType>>

    protected open suspend fun saveCallResult(data: ResponseType) { }
}