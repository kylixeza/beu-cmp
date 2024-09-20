package com.kylix.core.repositories.review

import com.github.michaelbull.result.Result
import com.kylix.core.util.Error
import com.kylix.core.util.Success

interface ReviewRepository {

    suspend fun postReview(
        historyId: String,
        stars: Int,
        comment: String,
        images: List<ByteArray>
    ): Result<Success<Unit>, Error>

}