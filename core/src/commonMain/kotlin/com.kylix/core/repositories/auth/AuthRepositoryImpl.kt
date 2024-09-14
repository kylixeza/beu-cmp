package com.kylix.core.repositories.auth

import co.touchlab.kermit.Logger
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.kylix.core.base.BaseNetworkRequest
import com.kylix.core.data.local.BeuDataStore
import com.kylix.core.data.remote.requests.LoginRequest
import com.kylix.core.data.remote.responses.BaseResponse
import com.kylix.core.data.remote.responses.token.TokenResponse
import com.kylix.core.data.remote.services.AuthService
import com.kylix.core.util.Success
import com.kylix.core.util.Error
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.builtins.NothingSerializer
import kotlinx.serialization.json.Json

class AuthRepositoryImpl(
    private val authService: AuthService,
    private val dataStore: BeuDataStore
) : AuthRepository {

    override suspend fun login(identifier: String, password: String): Result<Success<Unit>, Error> {
        return object : BaseNetworkRequest<Unit, TokenResponse>() {
            override suspend fun createCall(): HttpResponse {
                val body = LoginRequest(identifier, password)
                return authService.login(body)
            }

            override fun deserialize(responseJson: String): DeserializationStrategy<BaseResponse<TokenResponse>> {
                return BaseResponse.serializer(TokenResponse.serializer())
            }

            override suspend fun TokenResponse.mapResponse() {}

            override suspend fun saveCallResult(data: TokenResponse) {
                dataStore.saveToken(data.token)
            }

        }.run()
    }
}