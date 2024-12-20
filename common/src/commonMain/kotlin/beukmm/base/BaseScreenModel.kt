package beukmm.base

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

open class BaseScreenModel: ScreenModel {

    private val _uiState = MutableStateFlow(BaseUIState())
    val uiState = _uiState.asStateFlow()

    fun onStartLoading() {
        _uiState.update {
            it.copy(isLoading = true)
        }
    }

    fun onFinishLoading() {
        _uiState.update {
            it.copy(isLoading = false)
        }
    }

    fun onSuspendProcess(
        block: suspend () -> Unit
    ) {
        screenModelScope.launch(context = Dispatchers.IO) {
            onStartLoading()
            block()
            onFinishLoading()
        }
    }

    protected fun onDataError(message: String) {
        screenModelScope.launch {
            _uiState.update {
                it.copy(isLoading = false, isError = true, errorMessage = message)
            }
            delay(100)
            _uiState.update {
                it.copy(isError = false, errorMessage = "")
            }
        }
    }

    protected fun onDataSuccess() {
        _uiState.update {
            it.copy(isLoading = false, isSuccess = true)
        }
    }

    protected fun onResetUIState() {
        _uiState.update {
            it.copy(
                isLoading = false,
                isSuccess = false,
                isError = false,
                errorMessage = ""
            )
        }
    }

    override fun onDispose() {
        super.onDispose()
        onResetUIState()
    }

}