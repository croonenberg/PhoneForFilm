package com.example.phoneforfilm.util

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

object ThemeManager {
    private const val PREFS_NAME = "theme_prefs"
    private const val KEY_THEME = "selected_theme"

    // Voeg hier je eigen thema's toe (optioneel kun je styles koppelen aan displayName)
    enum class Theme(val displayName: String, val mode: Int) {
        GREENROOM("Greenroom", AppCompatDelegate.MODE_NIGHT_NO),
        BLUE_STAGE("Blue Stage", AppCompatDelegate.MODE_NIGHT_NO),
        DARKROOM("Darkroom", AppCompatDelegate.MODE_NIGHT_YES),
        NEUTRAL_LIGHT("Neutral Light", AppCompatDelegate.MODE_NIGHT_NO),
        GREY_CARD("Grey Card", AppCompatDelegate.MODE_NIGHT_NO)
    }

    fun applyTheme(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val name = prefs.getString(KEY_THEME, Theme.GREENROOM.name)
        val theme = Theme.valueOf(name ?: Theme.GREENROOM.name)
        AppCompatDelegate.setDefaultNightMode(theme.mode)
    }

    fun saveTheme(context: Context, theme: Theme) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_THEME, theme.name).apply()
    }

    fun getThemes(): List<Theme> = Theme.values().toList()
}
