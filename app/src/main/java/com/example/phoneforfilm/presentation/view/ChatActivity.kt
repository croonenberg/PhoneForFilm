package com.example.phoneforfilm.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.phoneforfilm.R
import com.example.phoneforfilm.databinding.ActivityChatBinding
import com.example.phoneforfilm.settings.SettingsViewModel
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding
    private val settingsViewModel: SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        // Thema toepassen op basis van opgeslagen key
        settingsViewModel.currentTheme.observe(this) { key ->
            val themeRes = when (key) {
                "Greenroom"    -> R.style.Theme_Greenroom
                "BlueStage"    -> R.style.Theme_BlueStage
                "GreyCard"     -> R.style.Theme_GreyCard
                "NeutralLight" -> R.style.Theme_NeutralLight
                "Darkroom"     -> R.style.Theme_Darkroom
                else           -> R.style.Theme_NeutralLight
            }
            setTheme(themeRes)
        }
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Bestaande functionaliteit...
        binding.btnSend.setOnClickListener {
            // send message logic
        }
        // Long-press context menu logic
        binding.rvMessages.adapter?.let { adapter ->
            // adapter long-press setup
        }
    }
}
