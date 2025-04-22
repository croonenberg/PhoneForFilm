package com.example.phoneforfilm.util

import com.example.phoneforfilm.R

object ThemeHelper {
    fun getThemeResId(key: String): Int {
        return when (key) {
            "wa" -> R.style.AppTheme_WhatsApp
            "im" -> R.style.AppTheme_iMessage
            "tg" -> R.style.AppTheme_Telegram
            else -> R.style.AppTheme_Base
        }
    }
}
