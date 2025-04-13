package com.example.phoneforfilm.viewmodel

import android.app.Activity
import android.content.Context
import android.view.Window
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import com.example.phoneforfilm.utils.PreferencesHelper
import java.util.*

class MainViewModel : ViewModel() {

    private lateinit var preferences: PreferencesHelper

    fun setPreferences(pref: PreferencesHelper) {
        preferences = pref
    }

    fun setDarkMode(enabled: Boolean) {
        AppCompatDelegate.setDefaultNightMode(
            if (enabled) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        )
        preferences.setDarkMode(enabled)
    }

    fun updateBrightness(window: Window, progress: Int) {
        val brightness = progress / 100.0f
        val lp = window.attributes
        lp.screenBrightness = brightness
        window.attributes = lp
    }

    fun saveBrightness(progress: Int) {
        val brightness = progress / 100.0f
        preferences.setBrightness(brightness)
    }

    fun setLanguage(context: Context, languageCode: Int) {
        val locale = when (languageCode) {
            0 -> "en"
            1 -> "nl"
            2 -> "de"
            3 -> "fr"
            4 -> "es"
            else -> "en"
        }
        val config = context.resources.configuration
        config.setLocale(Locale(locale))
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
        (context as Activity).recreate()
    }
}
