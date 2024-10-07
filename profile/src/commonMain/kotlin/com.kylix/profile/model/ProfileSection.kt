package com.kylix.profile.model

import org.jetbrains.compose.resources.DrawableResource

data class ProfileSection(
    val sectionName: String,
    val settings: List<Setting>
)

data class Setting(
    val name: String,
    val icon: DrawableResource,
    val setting: ProfileSetting,
)

enum class ProfileSetting {
    UPDATE_PROFILE,
    RESET_PASSWORD,
    HISTORY,
    FAVORITE,
    PRIVACY_POLICY,
    TERMS_AND_CONDITIONS,
    HELP
}
