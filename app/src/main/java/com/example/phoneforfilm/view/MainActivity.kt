package com.example.phoneforfilm.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.phoneforfilm.databinding.ActivityMainBinding
import com.example.phoneforfilm.utils.ThemeManager

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeManager.applyTheme(this)      // zet gekozen thema
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            startActivity(Intent(this, ChatListActivity::class.java))
        }

        binding.btnTheme.setOnClickListener {
            val themes = resources.getStringArray(R.array.theme_entries)
            AlertDialog.Builder(this)
                .setTitle(R.string.choose_theme)
                .setItems(themes) { _, which ->
                    ThemeManager.setTheme(this, themes[which])
                    recreate()
                }
                .show()
        }
    }
}