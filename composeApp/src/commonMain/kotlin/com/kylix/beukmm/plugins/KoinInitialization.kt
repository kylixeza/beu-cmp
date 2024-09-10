package com.kylix.beukmm.plugins

import com.kylix.core.di.dataStoreModule
import com.kylix.core.di.dataStorePlatformModule
import com.kylix.core.di.repositoriesModule
import com.kylix.onboard.di.onBoardModule
import com.kylix.splash.di.splashModule
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.logger.Logger
import org.koin.dsl.KoinAppDeclaration

fun installKoin(
    config: KoinAppDeclaration? = null
) {
    startKoin {
        config?.invoke(this)
        modules(
            dataStorePlatformModule,
            dataStoreModule,
            repositoriesModule,
            splashModule,
            onBoardModule
        )
    }
}