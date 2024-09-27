
package com.kylix.core.data.remote.responses.profile

import com.kylix.core.model.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val username: String,
    val name: String,
    val avatar: String,
    @SerialName("phone_number")
    val phoneNumber: String,
    val email: String?
) {
    fun toUser() = User(
        username = username,
        name = name,
        avatar = avatar,
        phoneNumber = phoneNumber,
        email = email
    )
}