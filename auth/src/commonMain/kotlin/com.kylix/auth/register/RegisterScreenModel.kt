package com.kylix.auth.register

import beukmm.base.BaseScreenModel
import cafe.adriel.voyager.core.model.ScreenModel
import com.kylix.core.repositories.auth.AuthRepository
import com.kylix.core.util.foldResult
import com.kylix.core.validator.validateConfirmPassword
import com.kylix.core.validator.validateEmail
import com.kylix.core.validator.validatePassword
import com.kylix.core.validator.validateUsername
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class RegisterScreenModel(
    private val repository: AuthRepository
): BaseScreenModel() {

    var registerState = MutableStateFlow(RegisterState())
        private set

    fun onUsernameChanged(username: String) {
        val usernameValidation = username.validateUsername()
        registerState.update {
            it.copy(
                username = username,
                isUsernameValid = usernameValidation to (usernameValidation == null)
            )
        }
    }

    fun onEmailChanged(email: String) {
        val emailValidation = email.validateEmail()
        registerState.update {
            it.copy(
                email = email,
                isEmailValid = emailValidation to (emailValidation == null)
            )
        }
    }

    fun onPasswordChanged(password: String) {
        val passwordValidation = password.validatePassword()
        registerState.update {
            it.copy(
                password = password,
                isPasswordValid = passwordValidation to (passwordValidation == null)
            )
        }
    }

    fun onConfirmPasswordChanged(confirmPassword: String) {
        val confirmPasswordValidation = confirmPassword.validateConfirmPassword(registerState.value.password)
        registerState.update {
            it.copy(
                confirmPassword = confirmPassword,
                isConfirmPasswordValid = confirmPasswordValidation to (confirmPasswordValidation == null)
            )
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

    fun register() {
        onSuspendProcess {
            repository.register(
                registerState.value.username,
                registerState.value.email,
                registerState.value.password
            ).foldResult(
                onSuccess = {
                    onDataSuccess()
                },
                onError = {
                    onDataError(it)
                }
            )
        }
    }
}

data class RegisterState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",

    val isPasswordVisible: Boolean = false,
    val isConfirmPasswordVisible: Boolean = false,

    val isUsernameValid: Pair<String?, Boolean> = null to false,
    val isEmailValid: Pair<String?, Boolean> = null to false,
    val isPasswordValid: Pair<String?, Boolean> = null to false,
    val isConfirmPasswordValid: Pair<String?, Boolean> = null to false,
)