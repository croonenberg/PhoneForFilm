package com.example.phoneforfilm

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.phoneforfilm.utils.PreferencesHelper

class MainActivity : AppCompatActivity() {

    private lateinit var preferences: PreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preferences = PreferencesHelper(this)

        val btnCall = findViewById<Button>(R.id.btnCall)
        val btnChat = findViewById<Button>(R.id.btnChat)
        val switchTheme = findViewById<androidx.appcompat.widget.SwitchCompat>(R.id.switchTheme)
        val brightnessSeekBar = findViewById<SeekBar>(R.id.brightnessSeekBar)

        // Call Simulation
        btnCall.setOnClickListener {
            startActivity(Intent(this, IncomingCallActivity::class.java))
        }

        // Chat Simulation
        btnChat.setOnClickListener {
            startActivity(Intent(this, ChatActivity::class.java))
        }

        // Theme switch
        switchTheme.isChecked = preferences.isDarkMode()
        switchTheme.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                preferences.setDarkMode(true)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                preferences.setDarkMode(false)
            }
        }

        // Brightness control
        brightnessSeekBar.progress = (preferences.getBrightness() * 100).toInt()
        brightnessSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val brightness = progress / 100.0f
                val lp = window.attributes
                lp.screenBrightness = brightness
                window.attributes = lp
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                val brightness = seekBar?.progress?.div(100.0f) ?: 0.5f
                preferences.setBrightness(brightness)
            }
        })
    }
}
