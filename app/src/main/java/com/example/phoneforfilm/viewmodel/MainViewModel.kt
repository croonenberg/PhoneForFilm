package com.example.phoneforfilm.viewmodel
@file:Suppress("unused", "UnusedImport")

import androidx.lifecycle.ViewModel
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.example.phoneforfilm.utils.PreferencesHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val preferences: PreferencesHelper
) : ViewModel() {

    fun isDarkMode(): Boolean = preferences.isDarkMode()

    fun setDarkMode(enabled: Boolean) {
        preferences.setDarkMode(enabled)
        AppCompatDelegate.setDefaultNightMode(
            if (enabled) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )
    }

    fun getBrightness(): Float = preferences.getBrightness()

    fun setBrightness(value: Float) {
        preferences.setBrightness(value)
    }

    fun getLanguage(): String = preferences.getLanguage()

    fun setLanguage(tag: String) {
        AppCompatDelegate.setApplicationLocales(
            LocaleListCompat.forLanguageTags(tag)
        )
        preferences.setLanguage(tag)
    }
}
