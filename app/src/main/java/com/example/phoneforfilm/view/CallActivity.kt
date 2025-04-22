package com.example.phoneforfilm.view

import android.os.Bundle
import com.example.phoneforfilm.view.BaseActivity
import com.example.phoneforfilm.databinding.ActivityCallBinding

class CallActivity : BaseActivity() {
    private lateinit var binding: ActivityCallBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCallBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEndCall.setOnClickListener {
            finish()
        }
    }
}
