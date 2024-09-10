package beukmm.base

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

open class BaseScreenModel: ScreenModel {

    open val _uiState = MutableStateFlow<BaseUIState?>(null)
    val uiState = _uiState.asStateFlow()

    protected fun onStartLoading() {
        _uiState.update {
            it?.copy(isLoading = true) ?: BaseUIState(isLoading = true)
        }
    }

    fun onFinishLoading() {
        _uiState.update {
            it?.copy(isLoading = false) ?: BaseUIState(isLoading = false)
        }
    }

    fun onSuspendProcess(
        block: suspend () -> Unit
    ) {
        screenModelScope.launch {
            onStartLoading()
            block()
            onFinishLoading()
        }
    }

    protected fun onDataError(message: String) {
        _uiState.update {
            it?.copy(isLoading = false, isError = true, errorMessage = message) ?: BaseUIState(isLoading = false, isError = true, errorMessage = message)
        }
    }

    protected fun onDataSuccess() {
        _uiState.update {
            it?.copy(isLoading = false, isSuccess = true) ?: BaseUIState(isLoading = false, isSuccess = true)
        }
    }

    protected fun onResetUIState() {
        _uiState.value = null
    }

    override fun onDispose() {
        super.onDispose()
        onResetUIState()
    }

}