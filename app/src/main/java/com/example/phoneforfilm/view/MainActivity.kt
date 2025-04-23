package com.example.phoneforfilm.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.phoneforfilm.R
import com.example.phoneforfilm.util.ThemeManager
import com.example.phoneforfilm.databinding.ActivityMainBinding
import com.example.phoneforfilm.view.BaseActivity

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeManager.applyTheme(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 🌐 Start Chat
        binding.btnStartChat.setOnClickListener {
            startActivity(Intent(this, ChatListActivity::class.java))
        }

        // 📞 Start Call
        binding.btnStartCallMain.setOnClickListener {
            startActivity(Intent(this, CallActivity::class.java))
        }

        // 🌈 Choose Theme
        binding.btnTheme.setOnClickListener {
            val themes = arrayOf("Greenroom", "Blue Stage", "Grey Card", "Neutral Light", "Darkroom")
            AlertDialog.Builder(this)
                .setTitle(R.string.choose_theme)
                .setItems(themes) { _, which ->
                    ThemeManager.setTheme(this, themes[which])
                    recreate()
                }
                .show()
        }

        // 🌐 Choose Language (optional)
        binding.btnLanguage.setOnClickListener {
            startActivity(Intent(this, LanguageSelectionActivity::class.java))
        }
    }
}