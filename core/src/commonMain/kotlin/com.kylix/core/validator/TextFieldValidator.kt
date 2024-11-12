package com.kylix.core.validator

fun String.validateUsername(): String? {
    return when {
        isEmpty() -> "Username must not be empty"
        length < 6 -> "Username must be at least 6 characters"
        contains(" ") -> "Username must not contain space"
        else -> null
    }
}

fun String.validateEmail(): String? {
    return when {
        isEmpty() -> "Email must not be empty"
        !contains("@") -> "Email is not valid"
        else -> null
    }
}

fun String.validatePassword(): String? {
    return when {
        isEmpty() -> "Password must not be empty"
        length < 8 -> "Password must be at least 8 characters"
        !matches("^(?=.*[A-Za-z])(?=.*\\d).+\$".toRegex()) -> "Password must contain at least 1 letter and 1 number"
        !matches("^(?=.*[!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).+\$".toRegex()) -> "Password must contain at least 1 special character"
        else -> null
    }
}

fun String.validateConfirmPassword(password: String): String? {
    return when {
        isEmpty() -> "Confirm password must not be empty"
        this != password -> "Confirm password must be the same as password"
        else -> null
    }
}
