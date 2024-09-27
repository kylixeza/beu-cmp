package com.kylix.core.repositories.profile

import com.github.michaelbull.result.Result
import com.kylix.core.model.User
import com.kylix.core.util.Error
import com.kylix.core.util.Success

interface ProfileRepository {
    suspend fun greet(): Result<Success<String>, Error>
    suspend fun getProfile(): Result<Success<User>, Error>
}