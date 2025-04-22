package com.example.phoneforfilm.view

import android.os.Bundle
import com.example.phoneforfilm.databinding.ActivityLanguageSelectionBinding

class LanguageSelectionActivity : BaseActivity() {

    private lateinit var binding: ActivityLanguageSelectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLanguageSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO: Implement language selection
    }
}
