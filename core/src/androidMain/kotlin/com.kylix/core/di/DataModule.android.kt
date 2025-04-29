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
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.plugin
import io.ktor.client.request.bearerAuth
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
    single {
        val context = get<Context>()

        val client = HttpClient(OkHttp) {
            engine {
                config {
                    connectTimeout(10, java.util.concurrent.TimeUnit.SECONDS)
                        .readTimeout(10, java.util.concurrent.TimeUnit.SECONDS)
                        .cache(
                            Cache(context.cacheDir, 10 * 1024 * 1024)
                        )
                }
            }
            beuDefaultRequest()
            beuDefaultLogging()
            beuDefaultContentNegotiation()
            beuDefaultRetries()
        }

       client.plugin(HttpSend).intercept { request ->
           val token = runBlocking { get<BeuDataStore>().getToken() }
           request.bearerAuth(token)
           execute(request)
        }

        client
    }
}