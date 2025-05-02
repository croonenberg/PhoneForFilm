package com.example.phoneforfilm.view

import dagger.hilt.android.AndroidEntryPoint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.phoneforfilm.utils.ThemeManager

open @AndroidEntryPoint
class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeManager.applyTheme(this)

    }
}
