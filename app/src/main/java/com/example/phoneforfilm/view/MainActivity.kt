package com.example.phoneforfilm.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.phoneforfilm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStartChat.setOnClickListener {
            startActivity(Intent(this, ChatActivity::class.java))
        }

        binding.btnStartCall.setOnClickListener {
            startActivity(Intent(this, CallActivity::class.java))
        }

        binding.btnLanguage.setOnClickListener {
            // Taal wijzigen functionaliteit (optioneel)
        }

        binding.btnTheme.setOnClickListener {
            // Thema wijzigen functionaliteit (optioneel)
        }
    }
}