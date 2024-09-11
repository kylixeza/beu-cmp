package com.kylix.core.di

import com.kylix.core.data.local.BeuDataStore
import com.kylix.core.data.remote.services.AuthService
import com.kylix.core.repositories.auth.AuthRepository
import com.kylix.core.repositories.auth.AuthRepositoryImpl
import com.kylix.core.repositories.splash.SplashRepository
import com.kylix.core.repositories.splash.SplashRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

expect val dataStorePlatformModule: Module

val dataStoreModule = module {
    single { BeuDataStore(get()) }
}

expect val networkPlatformModule: Module

val networkModule = module {
    single { AuthService(get()) }
}

val repositoriesModule = module {
    single<SplashRepository> { SplashRepositoryImpl(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
}