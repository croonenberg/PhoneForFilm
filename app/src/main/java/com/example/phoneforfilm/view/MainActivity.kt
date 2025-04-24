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
        ThemeManager.applyTheme(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 'btnStart' heette in sommige layouts 'btnStartChat'; probeer beide
        val startBtn =
            binding.root.findViewById<android.view.View>(R.id.btnStart)
                ?: binding.root.findViewById(R.id.btnStartChat)

        startBtn?.setOnClickListener {
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