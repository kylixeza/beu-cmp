package com.kylix.auth.di

import com.kylix.auth.login.LoginScreenModel
import org.koin.dsl.module

val authModule = module {
    factory { LoginScreenModel(get()) }
}