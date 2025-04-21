package com.example.phoneforfilm

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import java.util.Locale

class PhoneForFilmApp : Application() {
    override fun attachBaseContext(base: Context) {
        // Force default locale (English default resources)
        val config = Configuration(base.resources.configuration)
        config.setLocale(Locale.ENGLISH)
        val context = base.createConfigurationContext(config)
        super.attachBaseContext(context)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        // Ensure locale remains English
        val config = resources.configuration
        config.setLocale(Locale.ENGLISH)
        applyOverrideConfiguration(config)
    }
}
