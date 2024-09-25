package com.kylix.beukmm.plugins

import com.kylix.auth.di.authModule
import com.kylix.camera.di.cameraModule
import com.kylix.camera.di.tfLitePlatformModule
import com.kylix.core.di.dataStoreModule
import com.kylix.core.di.dataStorePlatformModule
import com.kylix.core.di.networkModule
import com.kylix.core.di.networkPlatformModule
import com.kylix.core.di.repositoriesModule
import com.kylix.detail.di.detailModule
import com.kylix.home.di.homeModule
import com.kylix.onboard.di.onBoardModule
import com.kylix.review.di.reviewModule
import com.kylix.splash.di.splashModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun installKoin(
    config: KoinAppDeclaration? = null
) {
    startKoin {
        config?.invoke(this)
        modules(
            dataStorePlatformModule,
            dataStoreModule,
            networkPlatformModule,
            networkModule,
            repositoriesModule,
            splashModule,
            onBoardModule,
            authModule,
            homeModule,
            detailModule,
            reviewModule,
            cameraModule,
            tfLitePlatformModule
        )
    }
}