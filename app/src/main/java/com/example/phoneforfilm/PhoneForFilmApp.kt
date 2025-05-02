package com.example.phoneforfilm

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PhoneForFilmApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // App-wide init if needed
    }
}
