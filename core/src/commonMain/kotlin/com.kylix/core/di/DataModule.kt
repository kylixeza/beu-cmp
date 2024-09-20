package com.kylix.core.di

import com.kylix.core.data.local.BeuDataStore
import com.kylix.core.data.remote.services.AuthService
import com.kylix.core.data.remote.services.FavoriteService
import com.kylix.core.data.remote.services.HistoryService
import com.kylix.core.data.remote.services.ProfileService
import com.kylix.core.data.remote.services.RecipeService
import com.kylix.core.data.remote.services.ReviewService
import com.kylix.core.repositories.auth.AuthRepository
import com.kylix.core.repositories.auth.AuthRepositoryImpl
import com.kylix.core.repositories.favorite.FavoriteRepository
import com.kylix.core.repositories.favorite.FavoriteRepositoryImpl
import com.kylix.core.repositories.history.HistoryRepository
import com.kylix.core.repositories.history.HistoryRepositoryImpl
import com.kylix.core.repositories.profile.ProfileRepository
import com.kylix.core.repositories.profile.ProfileRepositoryImpl
import com.kylix.core.repositories.recipe.RecipeRepository
import com.kylix.core.repositories.recipe.RecipeRepositoryImpl
import com.kylix.core.repositories.review.ReviewRepository
import com.kylix.core.repositories.review.ReviewRepositoryImpl
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
    single { RecipeService(get()) }
    single { ProfileService(get()) }
    single { FavoriteService(get()) }
    single { HistoryService(get()) }
    single { ReviewService(get()) }
}

val repositoriesModule = module {
    single<SplashRepository> { SplashRepositoryImpl(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    single<RecipeRepository> { RecipeRepositoryImpl(get()) }
    single<ProfileRepository> { ProfileRepositoryImpl(get()) }
    single<FavoriteRepository> { FavoriteRepositoryImpl(get()) }
    single<HistoryRepository> { HistoryRepositoryImpl(get()) }
    single<ReviewRepository> { ReviewRepositoryImpl(get()) }
}