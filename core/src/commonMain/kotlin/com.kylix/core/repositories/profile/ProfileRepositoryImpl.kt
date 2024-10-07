package com.kylix.core.repositories.profile

import com.github.michaelbull.result.Result
import com.kylix.core.base.BaseNetworkRequest
import com.kylix.core.data.remote.requests.PasswordRequest
import com.kylix.core.data.remote.requests.UserRequest
import com.kylix.core.data.remote.responses.BaseResponse
import com.kylix.core.data.remote.responses.profile.UserResponse
import com.kylix.core.data.remote.services.ProfileService
import com.kylix.core.model.User
import com.kylix.core.util.Error
import com.kylix.core.util.Success
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ProfileRepositoryImpl(
    private val profileService: ProfileService
): ProfileRepository {

    override suspend fun greet(): Result<Success<String>, Error> {
        return object : BaseNetworkRequest<String, String>() {
            override suspend fun createCall(): HttpResponse {
                return profileService.greet()
            }

            override fun deserialize(responseJson: String): DeserializationStrategy<BaseResponse<String>> {
                return BaseResponse.serializer(String.serializer())
            }

            override suspend fun String.mapResponse(): String {
                return this
            }

        }.run()
    }

    override suspend fun getProfile(): Result<Success<User>, Error> {
        return object : BaseNetworkRequest<User, UserResponse>() {
            override suspend fun createCall(): HttpResponse {
                return profileService.getProfile()
            }

            override fun deserialize(responseJson: String): DeserializationStrategy<BaseResponse<UserResponse>> {
                return BaseResponse.serializer(UserResponse.serializer())
            }

            override suspend fun UserResponse.mapResponse(): User {
                return this.toUser()
            }

        }.run()
    }

    override suspend fun updateProfile(
        user: User,
        newAvatar: ByteArray?
    ): Result<Success<Unit>, Error> {
        return object : BaseNetworkRequest<Unit, UserResponse>() {
            override suspend fun createCall(): HttpResponse {
                val body = UserRequest(
                    username = user.username,
                    name = user.name,
                    email = user.email,
                    phoneNumber = user.phoneNumber
                )
                val jsonBody = Json.encodeToString(body)
                return profileService.updateProfile(jsonBody, newAvatar)
            }

            override fun deserialize(responseJson: String): DeserializationStrategy<BaseResponse<UserResponse>> {
                return BaseResponse.serializer(UserResponse.serializer())
            }

            override suspend fun UserResponse.mapResponse() {
                return
            }

        }.run()
    }

    override suspend fun resetPassword(newPassword: String): Result<Success<Unit>, Error> {
        return object : BaseNetworkRequest<Unit, String>() {
            override suspend fun createCall(): HttpResponse {
                val body = PasswordRequest(newPassword)
                return profileService.resetPassword(body)
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