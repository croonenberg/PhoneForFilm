package com.example.phoneforfilm.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.phoneforfilm.R
import com.example.phoneforfilm.databinding.ActivityMainBinding
import com.example.phoneforfilm.utils.ThemeManager

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        // ✨ Thema toepassen vóór de layout geladen wordt
        ThemeManager.applyTheme(this)


        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // 🎨 Thema kiezen
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


        // 🌐 Chatknop
        binding.btnStartChat.setOnClickListener {
            startActivity(Intent(this, ChatListActivity::class.java))
        }

        // 📞 Belknop
        binding.btnStartCall.setOnClickListener {
            startActivity(Intent(this, CallActivity::class.java))
        }

        // 🌈 Thema kiezen
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

        // 🌐 Taal kiezen (optioneel)
        binding.btnLanguage.setOnClickListener {
            startActivity(Intent(this, LanguageSelectionActivity::class.java))
        }
    }
}
