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
        setSupportActionBar(binding.toolbar)

        // Start een telefoongesprek
            startActivity(Intent(this, CallActivity::class.java))
        }

        // Start chat-overzicht
            startActivity(Intent(this, ChatListActivity::class.java))
        }

        // Wijzig taal
            startActivity(Intent(this, LanguageSelectionActivity::class.java))
        }

        // Wijzig thema
            startActivity(Intent(this, ThemeSettingsActivity::class.java))
        }
    }
}
