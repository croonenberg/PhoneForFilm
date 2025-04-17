package com.example.phoneforfilm

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

        binding.btnChat.setOnClickListener {
            startActivity(Intent(this, ChatActivity::class.java))
        }

        binding.btnCall.setOnClickListener {
            startActivity(Intent(this, CallActivity::class.java))
        }
    }
}
