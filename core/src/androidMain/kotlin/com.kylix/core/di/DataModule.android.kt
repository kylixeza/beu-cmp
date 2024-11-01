package com.kylix.core.di

import android.content.Context
import com.kylix.core.data.local.BeuDataStore
import com.kylix.core.data.local.DataStoreFactory
import com.kylix.core.util.beuDefaultContentNegotiation
import com.kylix.core.util.beuDefaultLogging
import com.kylix.core.util.beuDefaultRequest
import com.kylix.core.util.beuDefaultRetries
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import kotlinx.coroutines.runBlocking
import okhttp3.Cache
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
actual val networkPlatformModule: Module = module {
    factory {
        val dataStore = get<BeuDataStore>()
        val token = runBlocking { dataStore.getToken() }
        val context = get<Context>()

        HttpClient(OkHttp) {
            engine {
                config {
                    connectTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
                        .readTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
                        .cache(
                            Cache(context.cacheDir, 10 * 1024 * 1024)
                        )
                }
            }
            beuDefaultRequest(token = token)
            beuDefaultLogging()
            beuDefaultContentNegotiation()
            beuDefaultRetries()
        }
    }
}