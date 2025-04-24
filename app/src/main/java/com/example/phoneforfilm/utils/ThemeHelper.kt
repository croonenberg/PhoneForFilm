package com.example.phoneforfilm.utils

import com.example.phoneforfilm.R

object ThemeHelper {
    fun overlayForTheme(themeName: String): Int = when (themeName) {
        "Greenroom"      -> R.style.ThemeOverlay_Chat_WhatsApp
        "Blue Stage"     -> R.style.ThemeOverlay_Chat_iMessage
        "Grey Card"      -> R.style.ThemeOverlay_Chat_GreyCard
        "Neutral Light"  -> R.style.ThemeOverlay_Chat_Telegram
        "Darkroom"       -> R.style.ThemeOverlay_Chat_Darkroom
        else             -> R.style.ThemeOverlay_Chat_WhatsApp
    }
}