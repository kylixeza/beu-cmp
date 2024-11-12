package com.kylix.auth.di

import com.kylix.auth.login.LoginScreenModel
import com.kylix.auth.register.RegisterScreenModel
import org.koin.dsl.module

val authModule = module {
    factory { LoginScreenModel(get()) }
    factory { RegisterScreenModel(get()) }
}