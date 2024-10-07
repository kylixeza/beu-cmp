package com.kylix.reset_password.model

data class PasswordTerms(
    val type: PasswordTermsType,
    val description: String,
    val isFulfilled: Boolean = false,
)

enum class PasswordTermsType {
    MINIMUM_8_CHARACTERS,
    ONE_WORD_ONE_CHARACTER,
    ONE_SPECIAL_CHARACTER,
}