package com.kylix.core.di

import android.content.Context
import com.kylix.core.data.local.DataStoreFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual val dataStorePlatformModule: Module = module {

    single {
        val factory = DataStoreFactory()

        factory.getDataStore(
            producePath = {
                val context = get<Context>()
                context.filesDir.resolve(DataStoreFactory.DATA_STORE_NAME).absolutePath
            }
        )
    }

}