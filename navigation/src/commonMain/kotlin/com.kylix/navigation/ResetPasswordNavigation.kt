package com.kylix.navigation

import beukmm.navigator.SharedScreen
import cafe.adriel.voyager.core.registry.screenModule
import com.kylix.reset_password.ResetPasswordScreen

val resetPasswordNavigationScreenModule = screenModule {
    register<SharedScreen.ResetPassword> {
        ResetPasswordScreen()
    }
}