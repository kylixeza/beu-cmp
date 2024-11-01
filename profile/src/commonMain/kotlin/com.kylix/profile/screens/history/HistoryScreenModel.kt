package com.kylix.profile.screens.history

import beukmm.base.BaseScreenModel
import com.kylix.core.model.History
import com.kylix.core.model.Review
import com.kylix.core.repositories.history.HistoryRepository
import com.kylix.core.repositories.review.ReviewRepository
import com.kylix.core.util.foldResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class HistoryScreenModel(
    private val historyRepository: HistoryRepository,
    private val reviewRepository: ReviewRepository
): BaseScreenModel() {

    var historyState = MutableStateFlow(HistoryState())
        private set

    fun getHistories() {
        onSuspendProcess {
            historyRepository.getHistories().foldResult(
                onSuccess = { result ->
                    historyState.update {
                        it.copy(histories = result)
                    }
                },
                onError = {
                    onDataError(it)
                }
            )
        }
    }

    fun getReview(historyId: String) {
        onSuspendProcess {
            reviewRepository.getReview(historyId).foldResult(
                onSuccess = { result ->
                    historyState.update {
                        it.copy(selectedReview = result)
                    }
                },
                onError = {
                    onDataError(it)
                }
            )
        }
    }

    fun onShowBottomSheet() {
        historyState.update {
            it.copy(showBottomSheet = true)
        }
    }

    fun onHideBottomSheet() {
        historyState.update {
            it.copy(showBottomSheet = false, selectedReview = null)
        }
    }
}

data class HistoryState(
    val histories: List<History> = emptyList(),
    val selectedReview: Review? = null,
    val showBottomSheet: Boolean = false
)