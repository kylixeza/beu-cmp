package com.kylix.profile.screens.help_center

import beukmm.base.BaseScreenModel
import com.kylix.core.repositories.help_center.HelpCenterRepository
import com.kylix.core.util.foldResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class HelpCenterScreenModel(
    private val helpCenterRepository: HelpCenterRepository
) : BaseScreenModel() {

    var helpCenterState = MutableStateFlow(HelpCenterState())
        private set

    fun sendEmail() {
        onSuspendProcess {
            helpCenterRepository.postMessage(helpCenterState.value.message).foldResult(
                onSuccess = {
                    onDataSuccess()
                    clearMessage()
                },
                onError = {
                    onDataError(it)
                }
            )
        }
    }

    private fun clearMessage() {
        helpCenterState.update { it.copy(message = "") }
    }

    fun setMessage(message: String) {
        helpCenterState.update {
            it.copy(message = message)
        }
    }

}

data class HelpCenterState(
    val message: String = ""
)