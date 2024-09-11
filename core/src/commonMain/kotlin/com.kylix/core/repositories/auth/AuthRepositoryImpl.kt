package com.kylix.core.repositories.auth

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.kylix.core.data.local.BeuDataStore
import com.kylix.core.data.remote.requests.LoginRequest
import com.kylix.core.data.remote.responses.BaseResponse
import com.kylix.core.data.remote.responses.TokenResponse
import com.kylix.core.data.remote.services.AuthService
import com.kylix.core.util.Success
import com.kylix.core.util.Error
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.builtins.NothingSerializer
import kotlinx.serialization.json.Json

class AuthRepositoryImpl(
    private val authService: AuthService,
    private val dataStore: BeuDataStore
) : AuthRepository {

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun login(identifier: String, password: String): Result<Success<Unit>, Error> {
        try {
            val body = LoginRequest(identifier, password)
            val response = authService.login(body)

            val result = response.bodyAsText()
            return if (response.status.value in (200..299)) {
                val token = Json.decodeFromString(BaseResponse.serializer(TokenResponse.serializer()), result)
                dataStore.saveToken(token.data?.token.orEmpty())
                Ok(Success(Unit))
            } else {
                val error = Json.decodeFromString(BaseResponse.serializer(NothingSerializer()), result)
                Err(Error(error.message))
            }
        } catch (e: Exception) {
            throw e
        }
    }
}