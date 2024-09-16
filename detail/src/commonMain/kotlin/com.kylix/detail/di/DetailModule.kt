package com.kylix.detail.di

import com.kylix.detail.DetailScreenModel
import org.koin.dsl.module

val detailModule = module {
    factory { DetailScreenModel(get(), get()) }
}