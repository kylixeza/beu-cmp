package com.kylix.beukmm.plugins

import com.kylix.auth.di.authModule
import com.kylix.core.di.dataStoreModule
import com.kylix.core.di.dataStorePlatformModule
import com.kylix.core.di.networkModule
import com.kylix.core.di.networkPlatformModule
import com.kylix.core.di.repositoriesModule
import com.kylix.onboard.di.onBoardModule
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
            authModule
        )
    }
}