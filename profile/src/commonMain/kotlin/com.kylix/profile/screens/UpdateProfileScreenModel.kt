package com.kylix.profile.screens

import androidx.compose.ui.graphics.ImageBitmap
import beukmm.base.BaseScreenModel
import beukmm.util.toByteArray
import com.kylix.core.model.User
import com.kylix.core.repositories.profile.ProfileRepository
import com.kylix.core.util.foldResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet

class UpdateProfileScreenModel(
    private val repository: ProfileRepository
): BaseScreenModel() {

    var updateProfileState = MutableStateFlow(UpdateProfileState())
        private set

    init {
        onSuspendProcess {
            repository.getProfile().foldResult(
                onSuccess = { result ->
                    updateProfileState.update {
                        it.copy(
                            user = result,
                            username = result.username,
                            email = result.email.orEmpty()
                        )
                    }
                },
                onError = {
                    onDataError(it)
                }
            )
        }
    }

    fun setNewAvatar(newAvatar: ImageBitmap) {
        updateProfileState.update {
            it.copy(newAvatar = newAvatar)
        }
    }

    fun setUsername(username: String) {
        updateProfileState.update {
            it.copy(username = username)
        }
    }

    fun setEmail(email: String) {
        updateProfileState.update {
            it.copy(email = email)
        }
    }

    fun setPickerState(open: Boolean) {
        updateProfileState.update {
            it.copy(openImagePicker = open)
        }
    }

    fun updateProfile() {
        val newUser = updateProfileState.value.user?.copy(
            username = updateProfileState.value.username,
            email = updateProfileState.value.email
        )
        val newAvatar = updateProfileState.value.newAvatar

        onSuspendProcess {
            repository.updateProfile(
                user = newUser ?: return@onSuspendProcess,
                newAvatar = newAvatar?.toByteArray()
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

data class UpdateProfileState(
    val user: User? = null,
    val username: String = "",
    val email: String = "",
    val newAvatar: ImageBitmap? = null,
    val openImagePicker: Boolean = false
)