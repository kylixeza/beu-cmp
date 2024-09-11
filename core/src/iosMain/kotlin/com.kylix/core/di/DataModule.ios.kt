package com.kylix.core.di

import com.kylix.core.data.local.BeuDataStore
import com.kylix.core.data.local.DataStoreFactory
import com.kylix.core.util.beuDefaultContentNegotiation
import com.kylix.core.util.beuDefaultLogging
import com.kylix.core.util.beuDefaultRequest
import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
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
    single {

        val dataStore = get<BeuDataStore>()
        val token = runBlocking { dataStore.getToken() }

        HttpClient(Darwin) {
            engine {
                configureRequest {
                    setAllowsCellularAccess(true)
                }
            }
            beuDefaultRequest(token = token)
            beuDefaultLogging()
            beuDefaultContentNegotiation()
        }
    }
}