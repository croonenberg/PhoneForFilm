package com.example.phoneforfilm.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repo: SettingsRepository
) : ViewModel() {
    val currentTheme = repo.themeFlow.asLiveData()

    fun setTheme(theme: String) {
        viewModelScope.launch {
            repo.saveTheme(theme)
        }
    }
}
