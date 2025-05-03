package com.example.phoneforfilm.utils

import javax.inject.Inject
import android.content.Context
import android.content.SharedPreferences

@javax.inject.Singleton
class PreferencesHelper @Inject constructor(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("PhoneForFilmPrefs", Context.MODE_PRIVATE)

    /* ---------- Dark mode ---------- */

    fun setDarkMode(enabled: Boolean) =
        prefs.edit().putBoolean("dark_mode", enabled).apply()

    fun isDarkMode(): Boolean =
        prefs.getBoolean("dark_mode", false)

    /* ---------- Brightness ---------- */

    fun setBrightness(value: Float) =
        prefs.edit().putFloat("brightness", value).apply()

    fun getBrightness(): Float =
        prefs.getFloat("brightness", 1f)

    /* ---------- Language ---------- */

    fun setLanguage(tag: String) =
        prefs.edit().putString("language", tag).apply()

    fun getLanguage(): String =
        prefs.getString("language", "en") ?: "en"
}
