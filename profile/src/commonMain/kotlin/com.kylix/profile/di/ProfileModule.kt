package com.kylix.profile.di

import com.kylix.profile.ProfileScreenModel
import com.kylix.profile.screens.UpdateProfileScreenModel
import org.koin.dsl.module

val profileModule = module {
    factory { ProfileScreenModel(get()) }
    factory { UpdateProfileScreenModel(get()) }
}