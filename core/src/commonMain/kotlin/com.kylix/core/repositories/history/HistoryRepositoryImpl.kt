package com.kylix.core.repositories.history

import com.github.michaelbull.result.Result
import com.kylix.core.base.BaseNetworkRequest
import com.kylix.core.data.remote.requests.HistoryRequest
import com.kylix.core.data.remote.responses.BaseResponse
import com.kylix.core.data.remote.responses.history.HistoryResponse
import com.kylix.core.data.remote.services.HistoryService
import com.kylix.core.model.History
import com.kylix.core.util.Error
import com.kylix.core.util.Success
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer

class HistoryRepositoryImpl(
    private val historyService: HistoryService
): HistoryRepository {

    override suspend fun postHistory(
        recipeId: String,
        timeSpent: Int
    ): Result<Success<String>, Error> {
        return object : BaseNetworkRequest<String, String>() {
            override suspend fun createCall(): HttpResponse {
                val body = HistoryRequest(
                    recipeId = recipeId,
                    timeSpent = timeSpent
                )
                return historyService.postHistory(body)
            }

            override fun deserialize(responseJson: String): DeserializationStrategy<BaseResponse<String>> {
                return BaseResponse.serializer(String.serializer())
            }

            override suspend fun String.mapResponse(): String {
                return this
            }

        }.run()
    }

    override suspend fun getHistories(): Result<Success<List<History>>, Error> {
        return object : BaseNetworkRequest<List<History>, List<HistoryResponse>>() {
            override suspend fun createCall(): HttpResponse {
                return historyService.getHistories()
            }

            override fun deserialize(responseJson: String): DeserializationStrategy<BaseResponse<List<HistoryResponse>>> {
                return BaseResponse.serializer(ListSerializer(HistoryResponse.serializer()))
            }

            override suspend fun List<HistoryResponse>.mapResponse(): List<History> {
                return map { it.toHistory() }
            }

        }.run()
    }
}