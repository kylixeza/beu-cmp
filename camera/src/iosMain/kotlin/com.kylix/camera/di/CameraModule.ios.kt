package com.kylix.camera.di

import com.kylix.camera.tflite.TFLiteHelper
import org.koin.core.module.Module
import org.koin.dsl.module

actual val tfLitePlatformModule: Module = module {
    single { TFLiteHelper() }
}