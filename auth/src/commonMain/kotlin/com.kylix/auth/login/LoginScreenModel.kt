package com.kylix.auth.login

import beukmm.base.BaseScreenModel
import cafe.adriel.voyager.core.model.ScreenModel
import com.kylix.core.repositories.auth.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class LoginScreenModel(
    private val authRepository: AuthRepository
) : BaseScreenModel() {

    var loginState = MutableStateFlow(LoginState())
        private set

    fun onIdentifierChanged(identifier: String) {
        loginState.update {
            it.copy(identifier = identifier)
        }
    }

    fun onPasswordChanged(password: String) {
        loginState.update {
            it.copy(password = password)
        }
    }

    fun onPasswordVisibilityChanged() {
        loginState.update {
            it.copy(isPasswordVisible = !it.isPasswordVisible)
        }
    }

    fun login() {
        onSuspendProcess {
            val result = authRepository.login(loginState.value.identifier, loginState.value.password)
            if (result.isOk) {
                onDataSuccess()
            } else {
                onDataError(result.error.message)
            }
        }
    }
}

data class LoginState(
    val identifier: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
)