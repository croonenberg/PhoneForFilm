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

        // Start een telefoongesprek
        binding.buttonStartCall.setOnClickListener {
            startActivity(Intent(this, CallActivity::class.java))
        }

        // Start chat-overzicht
        binding.buttonStartChat.setOnClickListener {
            startActivity(Intent(this, ChatListActivity::class.java))
        }

        // Toon contactlijst
        binding.buttonViewSubtitles.setOnClickListener {
            startActivity(Intent(this, ContactListActivity::class.java))
        }

        // Open thema-instellingen
        binding.buttonSubmitSubtitle.setOnClickListener {
            startActivity(Intent(this, ThemeSettingsActivity::class.java))
        }
    }
}
