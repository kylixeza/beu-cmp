package com.kylix.reset_password.di

import com.kylix.reset_password.ResetPasswordScreenModel
import org.koin.dsl.module

val resetPasswordModule = module {
    factory { ResetPasswordScreenModel(get()) }
}