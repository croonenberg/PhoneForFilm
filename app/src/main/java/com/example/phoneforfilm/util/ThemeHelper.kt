package com.example.phoneforfilm.util

import com.example.phoneforfilm.R

object ThemeHelper {
    fun getThemeResId(key: String): Int {
        return when (key) {
            "Greenroom" -> R.style.Theme_PhoneForFilm_Greenroom
            "BlueStage" -> R.style.Theme_PhoneForFilm_BlueStage
            "Darkroom" -> R.style.Theme_PhoneForFilm_Darkroom
            else -> R.style.Theme_PhoneForFilm
        }
    }
}
