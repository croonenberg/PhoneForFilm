package com.example.phoneforfilm.settings

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.preference.PreferenceManager

class SettingsRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val prefs: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    fun saveTheme(theme: String) {
        prefs.edit().putString("theme", theme).apply()
    }

    fun getTheme(): String? {
        return prefs.getString("theme", "Greenroom")
    }
}
