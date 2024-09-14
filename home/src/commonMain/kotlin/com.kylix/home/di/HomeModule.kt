package com.kylix.home.di

import com.kylix.home.HomeScreenModel
import org.koin.dsl.module

val homeModule = module {
    factory { HomeScreenModel(get(), get()) }
}