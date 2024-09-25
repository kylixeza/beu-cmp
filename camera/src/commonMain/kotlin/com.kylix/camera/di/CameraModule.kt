package com.kylix.camera.di

import com.kylix.camera.CameraScreenModel
import org.koin.core.module.Module
import org.koin.dsl.module

expect val tfLitePlatformModule: Module

val cameraModule = module {
    factory { CameraScreenModel(get()) }
}