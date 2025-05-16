package com.example.phoneforfilm.utils
@file:Suppress("unused", "UnusedImport")

import android.app.Activity
import com.example.phoneforfilm.R
import com.example.phoneforfilm.utils.ThemeMapper

object ThemeManager {

    fun applyTheme(activity: Activity, key: String?) {
        val themeRes = when (key?.lowercase()) {
            "greenroom"    -> R.style.Theme_PhoneForFilm_Greenroom
            "bluestage"    -> R.style.Theme_PhoneForFilm_Bluestage
            "greycard"     -> R.style.Theme_PhoneForFilm_Default
            "neutrallight" -> R.style.Theme_PhoneForFilm_Default
            "darkroom"     -> R.style.Theme_PhoneForFilm_Default
            else           -> R.style.Theme_PhoneForFilm_Default
        }
        activity.setTheme(themeRes)
    }
}
