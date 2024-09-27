package com.kylix.profile.di

import com.kylix.profile.ProfileScreenModel
import org.koin.dsl.module

val profileModule = module {
    factory { ProfileScreenModel(get()) }
}