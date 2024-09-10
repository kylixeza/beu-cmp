package com.kylix.core.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath

class DataStoreFactory {
    private lateinit var dataStore: DataStore<Preferences>

    fun getDataStore(producePath: () -> String): DataStore<Preferences> = if (::dataStore.isInitialized) {
        dataStore
    } else {
        PreferenceDataStoreFactory.createWithPath(produceFile = { producePath().toPath() })
            .also { dataStore = it }
    }

    companion object {
        const val DATA_STORE_NAME = "beu.preferences_pb"
    }

}