package com.exam.sevenseven.user

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPrefs(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "preferences")
        private val isUserLoggedIn = booleanPreferencesKey("is_login")
        private val username = stringPreferencesKey("username")
    }

    val isLogin: Flow<Boolean>
        get() = context.dataStore.data.map {
            it[isUserLoggedIn] ?: false
        }

    suspend fun setUserLogin(value: Boolean) {
        context.dataStore.edit { it[isUserLoggedIn] = value }
    }

    suspend fun removeUserLogin() {
        context.dataStore.edit { it.remove(isUserLoggedIn) }
    }

    val getUsername: Flow<String>
        get() = context.dataStore.data.map {
            it[username] ?: ""
        }

    suspend fun setUsername(value: String) {
        context.dataStore.edit { it[username] = value }
    }

    suspend fun removeUsername() {
        context.dataStore.edit { it.remove(username) }
    }
}