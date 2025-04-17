package com.example.phoneforfilm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.phoneforfilm.databinding.ActivityCallBinding

class CallActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCallBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCallBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.endCallButton.setOnClickListener {
            finish()
        }
    }
}
