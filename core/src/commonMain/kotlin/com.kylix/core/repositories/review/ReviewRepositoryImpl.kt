package com.kylix.core.repositories.review

import co.touchlab.kermit.Logger
import com.github.michaelbull.result.Result
import com.kylix.core.base.BaseNetworkRequest
import com.kylix.core.data.remote.responses.BaseResponse
import com.kylix.core.data.remote.responses.review.ReviewRequest
import com.kylix.core.data.remote.services.ReviewService
import com.kylix.core.util.Error
import com.kylix.core.util.Success
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json

class ReviewRepositoryImpl(
    private val reviewService: ReviewService
): ReviewRepository {

    override suspend fun postReview(
        historyId: String,
        stars: Int,
        comment: String,
        images: List<ByteArray>
    ): Result<Success<Unit>, Error> {
        return object : BaseNetworkRequest<Unit, String>() {
            override suspend fun createCall(): HttpResponse {
                val body = ReviewRequest(stars, comment)
                val jsonBody = Json.encodeToString(ReviewRequest.serializer(), body)
                Logger.i { "jsonBody: $jsonBody" }

                return reviewService.postReview(historyId, jsonBody, images)
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