package com.kylix.splash.di

import com.kylix.splash.screen.SplashScreenModel
import org.koin.dsl.module

val splashModule = module {
    factory { SplashScreenModel(get()) }
}