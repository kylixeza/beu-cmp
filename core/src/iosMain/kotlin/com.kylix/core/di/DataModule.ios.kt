package com.kylix.core.di

import com.kylix.core.data.local.BeuDataStore
import com.kylix.core.data.local.DataStoreFactory
import com.kylix.core.util.beuDefaultContentNegotiation
import com.kylix.core.util.beuDefaultLogging
import com.kylix.core.util.beuDefaultRequest
import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.runBlocking
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

@OptIn(ExperimentalForeignApi::class)
actual val dataStorePlatformModule: Module = module {
    single {
        val factory = DataStoreFactory()

        factory.getDataStore(
            producePath = {
                val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
                    directory = NSDocumentDirectory,
                    inDomain = NSUserDomainMask,
                    appropriateForURL = null,
                    create = false,
                    error = null
                )
                requireNotNull(documentDirectory).path+ "/${DataStoreFactory.DATA_STORE_NAME}"
            }
        )
    }
}
actual val networkPlatformModule: Module = module {
    factory {

        val dataStore = get<BeuDataStore>()
        val token = runBlocking { dataStore.getToken() }

        HttpClient(Darwin) {
            engine {
                configureRequest {
                    setAllowsCellularAccess(true)
                    setTimeoutInterval(30.0)
                    setAllowsConstrainedNetworkAccess(true)
                    setAllowsExpensiveNetworkAccess(true)
                }
            }
            beuDefaultRequest(token = token)
            beuDefaultLogging()
            beuDefaultContentNegotiation()
        }
    }
}