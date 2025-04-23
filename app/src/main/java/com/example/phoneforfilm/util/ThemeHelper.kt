
package com.example.phoneforfilm.util

import com.example.phoneforfilm.R

object ThemeHelper {

    /**
     * Koppelt een eenvoudige sleutel aan een ThemeOverlay resource‑id.
     * Deze overlays definiëren kleuren voor bubbles & primaries.
     */
    fun getThemeResId(key: String): Int = when (key) {
        "WhatsApp"   -> R.style.ThemeOverlay_Chat_WhatsApp
        "iMessage"   -> R.style.ThemeOverlay_Chat_iMessage
        "Telegram"   -> R.style.ThemeOverlay_Chat_Telegram
        else         -> R.style.ThemeOverlay_Chat_WhatsApp
    }
}
