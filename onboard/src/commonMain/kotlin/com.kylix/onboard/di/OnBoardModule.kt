package com.kylix.onboard.di

import com.kylix.onboard.screen.OnBoardScreenModel
import org.koin.dsl.module

val onBoardModule = module {
    factory { OnBoardScreenModel(get()) }
}