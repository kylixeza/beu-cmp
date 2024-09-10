package com.kylix.beukmm

import android.app.Application
import com.kylix.beukmm.plugins.installKoin
import com.kylix.beukmm.plugins.installNavigationRegistry
import org.koin.android.ext.koin.androidContext

class Beu: Application() {
    override fun onCreate() {
        super.onCreate()

        installNavigationRegistry()
        installKoin {
            androidContext(this@Beu)
        }
    }
}