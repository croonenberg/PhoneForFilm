package com.example.phoneforfilm.utils

import android.app.Activity
import android.content.Context
import androidx.core.content.edit
import com.example.phoneforfilm.R

object ThemeManager {

    private const val PREFS = "theme_prefs"
    private const val KEY   = "selected_theme"

    /**  Pas het opgeslagen thema toe vóór `super.onCreate()`  */
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

    /**  Sla een nieuw gekozen thema op  */
    fun save(context: Context, themeName: String) {
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE).edit {
            putString(KEY, themeName)
        }
    }

    /**  Alias voor legacy-code (`MainActivity.setTheme(...)`)  */
    fun setTheme(context: Context, themeName: String) = save(context, themeName)

    /**  Ophalen van huidige waarde (default = Greenroom)  */
    fun get(context: Context): String =
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .getString(KEY, "Greenroom") ?: "Greenroom"

/**
 * Opens a simple theme chooser dialog and calls [onChanged] when the user selects a theme
 */
fun showThemeChooser(activity: Activity, onChanged: () -> Unit = {}) {
    val themes = activity.resources.getStringArray(R.array.theme_entries)
    androidx.appcompat.app.AlertDialog.Builder(activity)
        .setTitle(R.string.choose_theme)
        .setItems(themes) { _, which ->
            setTheme(activity, themes[which])
            onChanged()
            activity.recreate()
        }
        .show()
}

}