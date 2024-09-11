package com.kylix.core.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first

class BeuDataStore(
    private val dataStore: DataStore<Preferences>
) {

    private val isPassOnBoardingKey = booleanPreferencesKey("is_pass_on_boarding")
    private val token = stringPreferencesKey("token")

    suspend fun saveIsPassOnBoarding(isPassOnBoarding: Boolean) {
        dataStore.edit { preferences ->
            preferences[isPassOnBoardingKey] = isPassOnBoarding
        }
    }

    suspend fun getIsPassOnBoarding(): Boolean {
        val preferences = dataStore.data.first()
        return preferences[isPassOnBoardingKey] ?: false
    }

    suspend fun saveToken(token: String) {
        dataStore.edit { preferences ->
            preferences[this.token] = token
        }
    }

    suspend fun getToken(): String {
        val preferences = dataStore.data.first()
        return preferences[token].orEmpty()
    }

}