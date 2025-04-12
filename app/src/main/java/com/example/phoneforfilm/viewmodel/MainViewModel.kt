package com.example.phoneforfilm.viewmodel

import android.view.Window
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import com.example.phoneforfilm.utils.PreferencesHelper

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
}
