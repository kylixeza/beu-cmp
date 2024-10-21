package com.kylix.core.repositories.help_center

import com.github.michaelbull.result.Result
import com.kylix.core.base.BaseNetworkRequest
import com.kylix.core.data.remote.requests.HelpCenterRequest
import com.kylix.core.data.remote.responses.BaseResponse
import com.kylix.core.data.remote.services.HelpCenterService
import com.kylix.core.util.Error
import com.kylix.core.util.Success
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.builtins.serializer

class HelpCenterRepositoryImpl(
    private val helpCenterApiService: HelpCenterService
): HelpCenterRepository {
    override suspend fun postMessage(message: String): Result<Success<Unit>, Error> {
        return object : BaseNetworkRequest<Unit, String>() {
            override suspend fun createCall(): HttpResponse {
                val body = HelpCenterRequest(message)
                return helpCenterApiService.postMessage(body)
            }

            override fun deserialize(responseJson: String): DeserializationStrategy<BaseResponse<String>> {
                return BaseResponse.serializer(String.serializer())
            }

            override suspend fun String.mapResponse() {
                return
            }

        }.run()
    }
}