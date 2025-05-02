package com.example.phoneforfilm.view

import com.example.phoneforfilm.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import com.example.phoneforfilm.databinding.ActivityLanguageSelectionBinding

class LanguageSelectionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLanguageSelectionBinding
    private val viewModel by viewModels<LanguageViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLanguageSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Observe current language
        viewModel.language.observe(this) { lang ->
            when (lang) {
                "en" -> binding.radioEn.isChecked = true
                "nl" -> binding.radioNl.isChecked = true
                "de" -> binding.radioDe.isChecked = true
                "fr" -> binding.radioFr.isChecked = true
                "es" -> binding.radioEs.isChecked = true
            }
        }

        // Handle user selection
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val newLang = when (checkedId) {
                R.id.radioEn -> "en"
                R.id.radioNl -> "nl"
                R.id.radioDe -> "de"
                R.id.radioFr -> "fr"
                R.id.radioEs -> "es"
                else -> null
            }
            newLang?.let { viewModel.setLanguage(it) }
        }

        // Initialize selection
        viewModel.getLanguage()?.let { lang ->
            when (lang) {
                "en" -> binding.radioEn.isChecked = true
                "nl" -> binding.radioNl.isChecked = true
                "de" -> binding.radioDe.isChecked = true
                "fr" -> binding.radioFr.isChecked = true
                "es" -> binding.radioEs.isChecked = true
            }
        }
    }
}
