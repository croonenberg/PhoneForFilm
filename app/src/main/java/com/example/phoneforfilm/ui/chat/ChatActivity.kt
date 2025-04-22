package com.example.phoneforfilm.ui.chat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.phoneforfilm.R
import com.example.phoneforfilm.util.ThemeHelper

class ChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Retrieve theme key passed via Intent
        val themeKey = intent.getStringExtra("theme_key") ?: "Greenroom"
        // Apply the selected theme before super.onCreate
        setTheme(ThemeHelper.getThemeResId(themeKey))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        // ...
    }
}
