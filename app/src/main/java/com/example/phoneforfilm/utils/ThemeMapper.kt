package com.example.phoneforfilm.utils

import com.example.phoneforfilm.R

/**
 * Maps a theme key stored in the database to its corresponding style resource.
 * Alleen hier uitbreiden als er écht een nieuw style‑bestand is toegevoegd;
 * zo blijft het risico op “unknown style id” compile‑errors nihil.
 */
object ThemeMapper {

    fun getStyleRes(themeKey: String?): Int = when (themeKey?.lowercase()) {
        // bestaande thema’s
        "greenroom"     -> R.style.Theme_PhoneForFilm_Greenroom
        "bluestage"     -> R.style.Theme_PhoneForFilm_Bluestage

        // nieuw toegevoegde thema‑sleutels
        "greycard"      -> R.style.Theme_GreyCard
        "neutrallight"  -> R.style.Theme_NeutralLight
        "darkroom"      -> R.style.Theme_Darkroom

        // fallback
        else            -> R.style.Theme_PhoneForFilm_Default
    }
}
