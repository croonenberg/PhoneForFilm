package com.example.phoneforfilm.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: SettingsRepository
) : ViewModel() {

    private val _currentTheme = MutableLiveData<String>()
    val currentTheme: LiveData<String> = _currentTheme

    init {
        _currentTheme.value = repository.getTheme()
    }

    fun setTheme(theme: String) {
        _currentTheme.value = theme
        repository.saveTheme(theme)
    }
}