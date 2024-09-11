package com.kylix.core.repositories.auth

import com.github.michaelbull.result.Result
import com.kylix.core.util.Error
import com.kylix.core.util.Success

interface AuthRepository {
    suspend fun login(identifier: String, password: String): Result<Success<Unit>, Error>
}