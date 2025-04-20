package com.example.phoneforfilm.utils

import android.app.Activity
import android.content.Context
import com.example.phoneforfilm.R

object ThemeManager {
    private const val PREFS_NAME = "theme_prefs"
    private const val KEY_THEME = "selected_theme"

    fun applyTheme(activity: Activity) {
        when (getSavedTheme(activity)) {
            "Greenroom" -> activity.setTheme(R.style.Theme_PhoneForFilm_Greenroom)
            "Blue Stage" -> activity.setTheme(R.style.Theme_PhoneForFilm_BlueStage)
            "Grey Card" -> activity.setTheme(R.style.Theme_PhoneForFilm_GreyCard)
            "Neutral Light" -> activity.setTheme(R.style.Theme_PhoneForFilm_NeutralLight)
            "Darkroom" -> activity.setTheme(R.style.Theme_PhoneForFilm_Darkroom)
            else -> activity.setTheme(R.style.Theme_PhoneForFilm)
        }
    }

    fun setTheme(context: Context, themeName: String) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_THEME, themeName).apply()
    }

    private fun getSavedTheme(context: Context): String? {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_THEME, "Greenroom")
    }
}
