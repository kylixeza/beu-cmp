package com.kylix.auth.register

import beukmm.base.BaseScreenModel
import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class RegisterScreenModel: BaseScreenModel() {

    var registerState = MutableStateFlow(RegisterState())
        private set

    fun onUsernameChanged(username: String) {
        registerState.update {
            it.copy(username = username)
        }
    }

    fun onNameChanged(name: String) {
        registerState.update {
            it.copy(name = name)
        }
    }

    fun onEmailChanged(email: String) {
        registerState.update {
            it.copy(email = email)
        }
    }

    fun onPasswordChanged(password: String) {
        registerState.update {
            it.copy(password = password)
        }
    }

    fun onConfirmPasswordChanged(confirmPassword: String) {
        registerState.update {
            it.copy(confirmPassword = confirmPassword)
        }
    }

    fun onPasswordVisibilityChanged() {
        registerState.update {
            it.copy(isPasswordVisible = !registerState.value.isPasswordVisible)
        }
    }

    fun onConfirmPasswordVisibilityChanged() {
        registerState.update {
            it.copy(isConfirmPasswordVisible = !registerState.value.isConfirmPasswordVisible)
        }
    }

}

data class RegisterState(
    val username: String = "",
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",

    val isPasswordVisible: Boolean = false,
    val isConfirmPasswordVisible: Boolean = false,
)