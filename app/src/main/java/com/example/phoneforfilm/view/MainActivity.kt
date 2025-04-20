package com.example.phoneforfilm.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog
import com.example.phoneforfilm.databinding.ActivityMainBinding
import com.example.phoneforfilm.util.ThemeManager
import com.example.phoneforfilm.view.LanguageSelectionActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        // ✨ Thema toepassen vóór de layout geladen wordt
        ThemeManager.applyTheme(this)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
            val themes = ThemeManager.getThemes()
            val names = themes.map { it.displayName }.toTypedArray()
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.choose_theme))
                .setItems(names) { _, which ->
                    val selected = themes[which]
                    ThemeManager.saveTheme(this, selected)
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
