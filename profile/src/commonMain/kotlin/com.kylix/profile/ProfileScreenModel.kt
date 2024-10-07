package com.kylix.profile

import beukmm.base.BaseScreenModel
import beukmm.profile.generated.resources.Res
import beukmm.profile.generated.resources.ic_profile_favorite
import beukmm.profile.generated.resources.ic_profile_help
import beukmm.profile.generated.resources.ic_profile_history
import beukmm.profile.generated.resources.ic_profile_privacy_policy
import beukmm.profile.generated.resources.ic_profile_terms_and_conditions
import beukmm.profile.generated.resources.ic_profile_update_password
import beukmm.profile.generated.resources.ic_profile_update_profile
import com.kylix.core.repositories.profile.ProfileRepository
import com.kylix.core.util.foldResult
import com.kylix.profile.model.ProfileSection
import com.kylix.profile.model.ProfileSetting
import com.kylix.profile.model.Setting
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class ProfileScreenModel(
    private val profileRepository: ProfileRepository
): BaseScreenModel() {

    var profileState = MutableStateFlow(ProfileState())
        private set

    fun getProfile() {
        onSuspendProcess {
            profileRepository.getProfile().foldResult(
                onSuccess = { profile ->
                    profileState.update {
                        it.copy(
                            username = profile.username,
                            avatar = profile.avatar,
                        )
                    }
                },
                onError = { error ->
                    onDataError(error)
                }
            )
        }
    }

    init {
        val sections = listOf(
            ProfileSection(
                sectionName = "Account",
                settings = listOf(
                    Setting(
                        name = "Update Profile",
                        icon = Res.drawable.ic_profile_update_profile,
                        setting = ProfileSetting.UPDATE_PROFILE
                    ),
                    Setting(
                        name = "Reset Password",
                        icon = Res.drawable.ic_profile_update_password,
                        setting = ProfileSetting.RESET_PASSWORD
                    ),
                    Setting(
                        name = "History",
                        icon = Res.drawable.ic_profile_history,
                        setting = ProfileSetting.HISTORY
                    ),
                    Setting(
                        name = "Favorite",
                        icon = Res.drawable.ic_profile_favorite,
                        setting = ProfileSetting.FAVORITE
                    ),
                )
            ),
            ProfileSection(
                sectionName = "Others",
                settings = listOf(
                    Setting(
                        name = "Privacy Policy",
                        icon = Res.drawable.ic_profile_privacy_policy,
                        setting = ProfileSetting.PRIVACY_POLICY
                    ),
                    Setting(
                        name = "Terms and Conditions",
                        icon = Res.drawable.ic_profile_terms_and_conditions,
                        setting = ProfileSetting.TERMS_AND_CONDITIONS
                    ),
                    Setting(
                        name = "Help Center",
                        icon = Res.drawable.ic_profile_help,
                        setting = ProfileSetting.HELP
                    )
                )
            )
        )

        profileState.update {
            it.copy(sections = sections)
        }
    }

}

data class ProfileState(
    val username: String = "",
    val avatar: String = "",
    val sections: List<ProfileSection> = emptyList()
)