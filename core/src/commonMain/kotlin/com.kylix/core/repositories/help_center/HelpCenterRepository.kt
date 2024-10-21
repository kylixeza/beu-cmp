package com.kylix.core.repositories.help_center

import com.github.michaelbull.result.Result
import com.kylix.core.util.Error
import com.kylix.core.util.Success

interface HelpCenterRepository {
    suspend fun postMessage(message: String): Result<Success<Unit>, Error>
}