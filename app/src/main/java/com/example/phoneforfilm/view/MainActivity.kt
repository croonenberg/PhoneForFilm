package com.example.phoneforfilm.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.example.phoneforfilm.R
import com.example.phoneforfilm.utils.PreferencesHelper
import com.example.phoneforfilm.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCall = findViewById<Button>(R.id.btnCall)
        val btnChat = findViewById<Button>(R.id.btnChat)
        val btnLanguage = findViewById<Button>(R.id.btnLanguage)
        val switchTheme = findViewById<SwitchCompat>(R.id.switchTheme)
        val brightnessSeekBar = findViewById<SeekBar>(R.id.brightnessSeekBar)

        viewModel.setPreferences(PreferencesHelper(this))

        btnCall.setOnClickListener {
            startActivity(Intent(this, IncomingCallActivity::class.java))
        }

        btnChat.setOnClickListener {
            startActivity(Intent(this, ChatActivity::class.java))
        }

        btnLanguage.setOnClickListener {
            val languages = arrayOf("English", "Nederlands", "Deutsch", "Français", "Español")
            androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Choose Language")
                .setItems(languages) { _, which ->
                    viewModel.setLanguage(this, which)
                }
                .show()
        }

        switchTheme.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setDarkMode(isChecked)
        }

        brightnessSeekBar.progress = (viewModel.preferences.getBrightness() * 100).toInt()

        brightnessSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                viewModel.updateBrightness(window, progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                viewModel.saveBrightness(seekBar?.progress ?: 100)
            }
        })
    }
}
