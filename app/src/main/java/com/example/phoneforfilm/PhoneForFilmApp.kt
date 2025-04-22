package com.example.phoneforfilm

import android.app.Application
import com.example.phoneforfilm.utils.ThemeManager

class PhoneForFilmApp : Application() {
    override fun onCreate() {
        super.onCreate()
        ThemeManager.applyTheme(this)
    }
}
