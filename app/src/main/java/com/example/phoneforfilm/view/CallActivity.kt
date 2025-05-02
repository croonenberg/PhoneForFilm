package com.example.phoneforfilm.view

import dagger.hilt.android.AndroidEntryPoint

import android.os.Bundle
import com.example.phoneforfilm.view.BaseActivity
import com.example.phoneforfilm.databinding.ActivityCallBinding

@AndroidEntryPoint
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