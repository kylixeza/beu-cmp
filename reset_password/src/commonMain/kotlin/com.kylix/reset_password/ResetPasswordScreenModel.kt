package com.kylix.reset_password

import beukmm.base.BaseScreenModel
import com.kylix.core.repositories.profile.ProfileRepository
import com.kylix.core.util.foldResult
import com.kylix.reset_password.model.PasswordTerms
import com.kylix.reset_password.model.PasswordTermsType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class ResetPasswordScreenModel(
    private val repository: ProfileRepository
): BaseScreenModel() {

    var resetPasswordState = MutableStateFlow(ResetPasswordState())
        private set

    init {
        resetPasswordState.update {
            it.copy(
                passwordTerms = listOf(
                    PasswordTerms(PasswordTermsType.MINIMUM_8_CHARACTERS, "8 characters minimum"),
                    PasswordTerms(PasswordTermsType.ONE_WORD_ONE_CHARACTER, "At least has 1 letter and 1 number"),
                    PasswordTerms(PasswordTermsType.ONE_SPECIAL_CHARACTER, "At least has 1 special character"),
                )
            )
        }
    }

    fun setPassword(password: String) {
        resetPasswordState.update { it.copy(password = password) }
        resetPasswordState.update { state ->
            state.copy(
                passwordTerms = state.passwordTerms.map { terms ->
                    val isFulfilled = when (terms.type) {
                        PasswordTermsType.MINIMUM_8_CHARACTERS -> password.length >= 8
                        PasswordTermsType.ONE_WORD_ONE_CHARACTER -> password.matches("^(?=.*[A-Za-z])(?=.*\\d).+\$".toRegex())
                        PasswordTermsType.ONE_SPECIAL_CHARACTER -> password.matches("^(?=.*[!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).+\$".toRegex())
                    }
                    terms.copy(isFulfilled = isFulfilled)
                }
            )
        }
    }

    fun setConfirmPassword(confirmPassword: String) {
        resetPasswordState.update { it.copy(confirmPassword = confirmPassword) }
    }

    fun resetPassword() {
        onSuspendProcess {
            repository.resetPassword(resetPasswordState.value.password).foldResult(
                onSuccess = {
                    onDataSuccess()
                },
                onError = {
                    onDataError(it)
                }
            )
        }
    }

    fun togglePasswordVisibility() {
        resetPasswordState.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
    }

    fun toggleConfirmPasswordVisibility() {
        resetPasswordState.update { it.copy(isConfirmPasswordVisible = !it.isConfirmPasswordVisible) }
    }

}

data class ResetPasswordState(
    val password: String = "",
    val confirmPassword: String = "",

    val isPasswordVisible: Boolean = false,
    val isConfirmPasswordVisible: Boolean = false,

    val passwordTerms: List<PasswordTerms> = emptyList()
)