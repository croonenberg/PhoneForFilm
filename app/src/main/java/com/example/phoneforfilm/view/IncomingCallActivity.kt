package com.example.phoneforfilm.view

import dagger.hilt.android.AndroidEntryPoint

import android.os.Bundle
import com.example.phoneforfilm.view.BaseActivity
import com.example.phoneforfilm.databinding.ActivityIncomingCallBinding

@AndroidEntryPoint
class IncomingCallActivity : BaseActivity() {
    private lateinit var binding: ActivityIncomingCallBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIncomingCallBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}