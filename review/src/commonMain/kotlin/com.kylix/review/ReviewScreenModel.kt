package com.kylix.review

import androidx.compose.ui.graphics.ImageBitmap
import beukmm.base.BaseScreenModel
import beukmm.util.toByteArray
import cafe.adriel.voyager.core.model.screenModelScope
import co.touchlab.kermit.Logger
import com.kylix.core.repositories.review.ReviewRepository
import com.kylix.core.util.foldResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ReviewScreenModel(
    private val repository: ReviewRepository
) : BaseScreenModel() {

    var reviewState = MutableStateFlow(ReviewState())
        private set

    fun setStars(stars: Int) {
        Logger.i { "setStars: $stars" }
        reviewState.update {
            it.copy(stars = stars)
        }
    }

    fun setComment(comment: String) {
        reviewState.update {
            it.copy(comment = comment)
        }
    }

    fun addImage(image: ImageBitmap) {
        reviewState.update {
            it.copy(images = it.images + image)
        }
    }

    fun removeImage(image: ImageBitmap) {
        reviewState.update {
            it.copy(images = it.images - image)
        }
    }

    fun setPickerState(open: Boolean) {
        reviewState.update {
            it.copy(openImagePicker = open)
        }
    }

    fun submitReview(historyId: String) {
        screenModelScope.launch(Dispatchers.IO) {
            onStartLoading()

            val imagesByteArray = async(Dispatchers.Main) {
                reviewState.value.images.map { it.toByteArray() }
            }.await()

            repository.postReview(
                historyId,
                reviewState.value.stars,
                reviewState.value.comment,
                imagesByteArray
            ).foldResult(
                onSuccess = { onDataSuccess() },
                onError = { onDataError(it) }
            )

            onFinishLoading()
        }
    }

}

data class ReviewState(
    val stars: Int = 0,
    val comment: String = "",
    val images: List<ImageBitmap> = emptyList(),
    val openImagePicker: Boolean = false
)