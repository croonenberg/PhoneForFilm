package com.example.phoneforfilm

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import java.util.Locale

class PhoneForFilmApp : Application() {
    override fun attachBaseContext(base: Context) {
        val defaultConfig = Configuration(base.resources.configuration)
        // Use ROOT locale to force default resources from res/values/
        defaultConfig.setLocale(Locale.ROOT)
        val context = base.createConfigurationContext(defaultConfig)
        super.attachBaseContext(context)
    }
}
