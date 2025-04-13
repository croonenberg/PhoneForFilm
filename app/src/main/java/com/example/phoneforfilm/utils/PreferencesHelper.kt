package com.example.phoneforfilm.utils

import android.content.Context
import android.content.SharedPreferences

class PreferencesHelper(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("PhoneForFilmPrefs", Context.MODE_PRIVATE)

    fun setDarkMode(enabled: Boolean) {
        sharedPreferences.edit().putBoolean("dark_mode", enabled).apply()
    }

    fun isDarkMode(): Boolean {
        return sharedPreferences.getBoolean("dark_mode", false)
    }

    fun setBrightness(brightness: Float) {
        sharedPreferences.edit().putFloat("brightness", brightness).apply()
    }

    fun getBrightness(): Float {
        return sharedPreferences.getFloat("brightness", 1.0f)
    }
}
