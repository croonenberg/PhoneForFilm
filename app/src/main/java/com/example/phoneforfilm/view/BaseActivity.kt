package com.example.phoneforfilm.view

import android.os.Bundle
import com.example.phoneforfilm.view.BaseActivity
import com.example.phoneforfilm.utils.ThemeManager

open class BaseActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeManager.applyTheme(this)
        super.onCreate(savedInstanceState)
    }
}
