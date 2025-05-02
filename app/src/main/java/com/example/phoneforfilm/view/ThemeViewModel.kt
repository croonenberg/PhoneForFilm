package com.example.phoneforfilm.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ThemeViewModel : ViewModel() {
    private val _isDarkMode = MutableLiveData<Boolean>()
    val isDarkMode: LiveData<Boolean> = _isDarkMode

    fun getDarkMode(): Boolean? = _isDarkMode.value
    fun setDarkMode(enabled: Boolean) {
        _isDarkMode.value = enabled
    }

    private val _brightness = MutableLiveData<Float>()
    val brightness: LiveData<Float> = _brightness

    fun getBrightness(): Float? = _brightness.value
    fun setBrightness(level: Float) {
        _brightness.value = level
    }
}
