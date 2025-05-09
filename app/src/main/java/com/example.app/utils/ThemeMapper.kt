package com.example.app.utils

import com.example.app.R

object ThemeMapper {
    fun getStyleRes(themeKey: String?): Int {
        return when (themeKey) {
            "greenroom" -> R.style.Theme_PhoneForFilm_Greenroom
            "bluestage" -> R.style.Theme_PhoneForFilm_Bluestage
            // add other themes...
            else -> R.style.Theme_PhoneForFilm_Default
        }
    }
}
