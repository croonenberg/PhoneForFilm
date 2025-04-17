package com.example.phoneforfilm.viewmodel

import android.content.Context
import android.view.Window
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import com.example.phoneforfilm.utils.PreferencesHelper

class MainViewModel : ViewModel() {

    private lateinit var preferences: PreferencesHelper

    fun setPreferences(pref: PreferencesHelper) {
        preferences = pref
    }

    /* ---------- Thema ---------- */

    fun setDarkMode(enabled: Boolean) {
        AppCompatDelegate.setDefaultNightMode(
            if (enabled) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )
        preferences.setDarkMode(enabled)
    }

    /* ---------- Helderheid ---------- */

    fun updateBrightness(window: Window, progress: Int) {
        val brightness = progress / 100f
        val lp = window.attributes
        lp.screenBrightness = brightness
        window.attributes = lp
    }

    /* ---------- Taal ---------- */

    /**
     * languageIndex → 0 = en, 1 = nl, 2 = de, 3 = fr, 4 = es
     */
    fun setLanguage(context: Context, languageIndex: Int) {
        val tag = when (languageIndex) {
            1 -> "nl"
            2 -> "de"
            3 -> "fr"
            4 -> "es"
            else -> "en"
        }

        // Nieuwe, niet‑verouderde API (AppCompat 1.6+)
        AppCompatDelegate.setApplicationLocales(
            LocaleListCompat.forLanguageTags(tag)
        )

        preferences.setLanguage(tag)
    }
}
