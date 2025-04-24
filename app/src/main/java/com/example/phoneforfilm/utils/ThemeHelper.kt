package com.example.phoneforfilm.utils

import com.example.phoneforfilm.R

/**
 * Resolves a simple theme‑key (chosen per conversation) to a style resource id.
 * The styles themselves are defined in res/values/theme_overlays_chat.xml
 */
object ThemeHelper {
    fun getThemeResId(key: String): Int = when (key) {
        "WhatsApp", "Greenroom" -> R.style.ThemeOverlay_Chat_WhatsApp
        "iMessage", "Blue Stage" -> R.style.ThemeOverlay_Chat_iMessage
        "Telegram" -> R.style.ThemeOverlay_Chat_Telegram
        else -> R.style.ThemeOverlay_Chat_WhatsApp
    }
}