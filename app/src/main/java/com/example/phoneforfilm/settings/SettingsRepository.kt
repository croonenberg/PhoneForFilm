package com.example.phoneforfilm.settings

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

@Singleton
class SettingsRepository @Inject constructor(context: Context) {
    private val prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
    private val key = "pref_theme"
    private val _themeFlow = MutableStateFlow(prefs.getString(key, "NeutralLight")!!)
    val themeFlow: Flow<String> = _themeFlow

    fun saveTheme(theme: String) {
        prefs.edit().putString(key, theme).apply()
        _themeFlow.value = theme
    }
}
