package com.example.phoneforfilm.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


private val Context.dataStore by preferencesDataStore(name = "settings")

object PreferencesHelper {
    private val THEME_KEY = intPreferencesKey("theme_key")
    private val LOCALE_KEY = stringPreferencesKey("locale_key")

    fun getTheme(context: Context): Flow<Int> =
        context.dataStore.data.map { prefs -> prefs[THEME_KEY] ?: 0 }

    suspend fun setTheme(context: Context, theme: Int) {
        context.dataStore.edit { prefs -> prefs[THEME_KEY] = theme }
    }

    fun getLocale(context: Context): Flow<String> =
        context.dataStore.data.map { prefs -> prefs[LOCALE_KEY] ?: "en" }

    suspend fun setLocale(context: Context, locale: String) {
        context.dataStore.edit { prefs -> prefs[LOCALE_KEY] = locale }
    }
}
