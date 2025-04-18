package com.example.phoneforfilm.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.phoneforfilm.databinding.ActivityIncomingCallBinding

class IncomingCallActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIncomingCallBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIncomingCallBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAnswer.setOnClickListener {
            // Handle answer
        }

        binding.btnDecline.setOnClickListener {
            // Handle decline
        }
    }
}