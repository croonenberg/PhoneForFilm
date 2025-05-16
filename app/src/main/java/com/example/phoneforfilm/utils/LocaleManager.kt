
package com.example.phoneforfilm.utils
@file:Suppress("unused", "UnusedImport")

import android.content.Context
import android.content.res.Configuration
import androidx.core.content.edit
import java.util.*

object LocaleManager {
    private const val PREFS = "locale_prefs"
    private const val KEY = "selected_locale"

    /** Apply stored locale to base context and return wrapped context */
    fun applyStoredLocale(base: Context): Context {
        val localeTag = base.getSharedPreferences(PREFS, Context.MODE_PRIVATE).getString(KEY, Locale.getDefault().toLanguageTag())
        val locale = Locale.forLanguageTag(localeTag ?: Locale.getDefault().toLanguageTag())
        Locale.setDefault(locale)

        val config = Configuration(base.resources.configuration)
        config.setLocale(locale)
        return base.createConfigurationContext(config)
    }

    fun setLocale(context: Context, locale: Locale) {
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE).edit {
            putString(KEY, locale.toLanguageTag())
        }
    }

    fun getCurrentLocale(context: Context): Locale {
        val tag = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE).getString(KEY, Locale.getDefault().toLanguageTag())
        return Locale.forLanguageTag(tag ?: "en")
    }
}
