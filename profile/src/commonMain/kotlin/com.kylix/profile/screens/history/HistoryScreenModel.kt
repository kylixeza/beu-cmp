package com.kylix.profile.screens.history

import beukmm.base.BaseScreenModel
import com.kylix.core.model.History
import com.kylix.core.repositories.history.HistoryRepository
import com.kylix.core.util.foldResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class HistoryScreenModel(
    private val repository: HistoryRepository
): BaseScreenModel() {

    var historyState = MutableStateFlow(HistoryState())
        private set

    fun getHistories() {
        onSuspendProcess {
            repository.getHistories().foldResult(
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
}

data class HistoryState(
    val histories: List<History> = emptyList()
)