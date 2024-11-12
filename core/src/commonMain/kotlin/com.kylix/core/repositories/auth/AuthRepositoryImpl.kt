package com.kylix.core.repositories.auth

import com.github.michaelbull.result.Result
import com.kylix.core.base.BaseNetworkRequest
import com.kylix.core.data.local.BeuDataStore
import com.kylix.core.data.remote.requests.LoginRequest
import com.kylix.core.data.remote.requests.RegisterRequest
import com.kylix.core.data.remote.responses.BaseResponse
import com.kylix.core.data.remote.responses.token.TokenResponse
import com.kylix.core.data.remote.services.AuthService
import com.kylix.core.util.Error
import com.kylix.core.util.Success
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.builtins.serializer

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

    override suspend fun register(
        username: String,
        email: String,
        password: String
    ): Result<Success<Unit>, Error> {
        return object : BaseNetworkRequest<Unit, TokenResponse>() {
            override suspend fun createCall(): HttpResponse {
                val body = RegisterRequest(username, email, password)
                return authService.register(body)
            }

            override fun deserialize(responseJson: String): DeserializationStrategy<BaseResponse<TokenResponse>> {
                return BaseResponse.serializer(TokenResponse.serializer())
            }

            override suspend fun TokenResponse.mapResponse() {}

            override suspend fun saveCallResult(data: TokenResponse) {}

        }.run()
    }

    override suspend fun logout(): Result<Success<Unit>, Error> {
        return object : BaseNetworkRequest<Unit, String>() {
            override suspend fun createCall(): HttpResponse {
                return authService.logout()
            }

            override fun deserialize(responseJson: String): DeserializationStrategy<BaseResponse<String>> {
                return BaseResponse.serializer(String.serializer())
            }

            override suspend fun String.mapResponse() {}

            override suspend fun saveCallResult(data: String) {
                dataStore.clearToken()
            }

        }.run()
    }
}