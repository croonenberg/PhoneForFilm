package com.example.phoneforfilm.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.phoneforfilm.R
import com.example.phoneforfilm.databinding.ActivityChatsBinding
import com.example.phoneforfilm.settings.SettingsViewModel
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatsBinding
    private val settingsViewModel: SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
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
        binding = ActivityChatsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // existing setup...
    }
}
