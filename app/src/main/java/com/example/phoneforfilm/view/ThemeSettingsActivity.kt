package com.example.phoneforfilm.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import com.example.phoneforfilm.databinding.ActivityThemeSettingsBinding

class ThemeSettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThemeSettingsBinding
    private val viewModel by viewModels<ThemeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThemeSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Observe dark mode setting
        viewModel.isDarkMode.observe(this) { enabled ->
            binding.switchDarkMode.isChecked = enabled
        }
        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setDarkMode(isChecked)
        }

        // Observe brightness setting
        viewModel.brightness.observe(this) { level ->
            binding.sliderBrightness.value = level
        }
        binding.sliderBrightness.addOnChangeListener { _, value, _ ->
            viewModel.setBrightness(value)
        }

        // Initialize controls
        viewModel.getDarkMode()?.let { binding.switchDarkMode.isChecked = it }
        viewModel.getBrightness()?.let { binding.sliderBrightness.value = it }
        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setDarkMode(isChecked)
        }
    }
}
