package com.example.phoneforfilm.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.phoneforfilm.databinding.ActivityMainBinding
import com.example.phoneforfilm.utils.PreferencesHelper
import com.example.phoneforfilm.viewmodel.MainViewModel
import android.widget.SeekBar
import com.example.phoneforfilm.R

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var preferences: PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferences = PreferencesHelper(this)
        viewModel.setPreferences(preferences)

        // Dark mode switch
        binding.switchTheme.isChecked = preferences.isDarkMode()
        binding.switchTheme.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setDarkMode(isChecked)
        }

        // Brightness SeekBar
        val savedBrightness = (preferences.getBrightness() * 100).toInt()
        binding.brightnessSeekBar.progress = savedBrightness

        binding.brightnessSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                viewModel.updateBrightness(window, progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                viewModel.saveBrightness(seekBar?.progress ?: 50)
            }
        })

        // Language selection
        binding.languageButton.setOnClickListener {
            val selectedLanguage = binding.languageSpinner.selectedItemPosition
            viewModel.setLanguage(this, selectedLanguage)
        }

        // Navigate to Chat Screen
        binding.startChatButton.setOnClickListener {
            startActivity(ChatActivity.newIntent(this))
        }

        // Navigate to Call Screen
        binding.startCallButton.setOnClickListener {
            startActivity(CallActivity.newIntent(this))
        }
    }
}
