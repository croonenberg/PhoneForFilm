package com.example.phoneforfilm.view

import dagger.hilt.android.AndroidEntryPoint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.phoneforfilm.utils.ThemeManager
import android.content.Context

@AndroidEntryPoint
open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Lees opgeslagen thema-key uit SharedPreferences
        val prefs = getSharedPreferences("settings", Context.MODE_PRIVATE)
        val key = prefs.getString("pref_theme", "NeutralLight") ?: "NeutralLight"
        // Pas thema toe met key
        ThemeManager.applyTheme(this, key)

        super.onCreate(savedInstanceState)
    }
}
