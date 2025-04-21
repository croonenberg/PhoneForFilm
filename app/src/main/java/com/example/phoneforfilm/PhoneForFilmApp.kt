package com.example.phoneforfilm

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import java.util.Locale

class PhoneForFilmApp : Application() {
    override fun attachBaseContext(base: Context) {
        // Force default locale to English
        val config = Configuration(base.resources.configuration)
        config.setLocale(Locale.ENGLISH)
        val localizedContext = base.createConfigurationContext(config)
        super.attachBaseContext(localizedContext)
    }
}
