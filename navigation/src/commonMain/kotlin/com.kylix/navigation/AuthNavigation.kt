package com.kylix.navigation

import beukmm.navigator.SharedScreen
import cafe.adriel.voyager.core.registry.screenModule
import com.kylix.auth.login.LoginScreen
import com.kylix.auth.register.RegisterScreen

val authNavigationScreenModule = screenModule {
    register<SharedScreen.Login> {
        LoginScreen()
    }
    register<SharedScreen.Register> {
        RegisterScreen()
    }
}