package com.example.phoneforfilm.presentation.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.phoneforfilm.databinding.ActivityChatBinding
import com.example.phoneforfilm.settings.SettingsViewModel
import com.example.phoneforfilm.utils.ThemeManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private val settingsViewModel: SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        settingsViewModel.currentTheme.observe(this, Observer { themeKey ->
            ThemeManager.applyTheme(this, themeKey)
        })

        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Rest van de setup...
    }
}
