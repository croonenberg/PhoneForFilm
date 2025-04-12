package com.example.phoneforfilm.utils

import android.content.Context
import android.content.SharedPreferences

class PreferencesHelper(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("phone_for_film_prefs", Context.MODE_PRIVATE)

    fun isDarkMode(): Boolean {
        return prefs.getBoolean("dark_mode", false)
    }

    fun setDarkMode(enabled: Boolean) {
        prefs.edit().putBoolean("dark_mode", enabled).apply()
    }

    fun getBrightness(): Float {
        return prefs.getFloat("brightness", 0.5f)
    }

    fun setBrightness(value: Float) {
        prefs.edit().putFloat("brightness", value).apply()
    }
}
