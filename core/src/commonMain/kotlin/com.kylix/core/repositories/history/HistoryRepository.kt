package com.kylix.core.repositories.history

import com.github.michaelbull.result.Result
import com.kylix.core.model.History
import com.kylix.core.util.Error
import com.kylix.core.util.Success

interface HistoryRepository {

    suspend fun postHistory(
        recipeId: String,
        timeSpent: Int,
    ): Result<Success<String>, Error>

    suspend fun getHistories(): Result<Success<List<History>>, Error>

}