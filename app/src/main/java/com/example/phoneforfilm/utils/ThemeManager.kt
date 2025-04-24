package com.example.phoneforfilm.utils

import android.app.Activity
import android.content.Context
import com.example.phoneforfilm.R

object ThemeManager {
    private const val PREFS = "theme_prefs"
    private const val KEY   = "selected_theme"

    fun applyTheme(activity: Activity) {
        when (get(activity)) {
            "Greenroom"      -> activity.setTheme(R.style.Theme_PhoneForFilm_Greenroom)
            "Blue Stage"     -> activity.setTheme(R.style.Theme_PhoneForFilm_BlueStage)
            "Grey Card"      -> activity.setTheme(R.style.Theme_PhoneForFilm_GreyCard)
            "Neutral Light"  -> activity.setTheme(R.style.Theme_PhoneForFilm_NeutralLight)
            "Darkroom"       -> activity.setTheme(R.style.Theme_PhoneForFilm_Darkroom)
            else             -> activity.setTheme(R.style.Theme_PhoneForFilm)
        }
    }

    fun save(context: Context, theme: String) {
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .edit().putString(KEY, theme).apply()
    }

    fun get(context: Context): String =
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .getString(KEY, "Greenroom") ?: "Greenroom"
}