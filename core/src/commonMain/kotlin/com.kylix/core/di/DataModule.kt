package com.kylix.core.di

import com.kylix.core.data.local.BeuDataStore
import com.kylix.core.repositories.SplashRepository
import com.kylix.core.repositories.SplashRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

expect val dataStorePlatformModule: Module

val dataStoreModule = module {
    single { BeuDataStore(get()) }
}

val repositoriesModule = module {
    single<SplashRepository> { SplashRepositoryImpl(get()) }
}