package com.example.phoneforfilm.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phoneforfilm.domain.usecase.GetConversationThemeUseCase
import com.example.phoneforfilm.domain.usecase.SetConversationThemeUseCase
import com.example.phoneforfilm.ui.common.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val getTheme: GetConversationThemeUseCase,
    private val setTheme: SetConversationThemeUseCase
) : ViewModel() {

    private val _defaultTheme = MutableStateFlow<UIState<String?>>(UIState.Loading)
    val defaultTheme: StateFlow<UIState<String?>> = _defaultTheme

    fun loadDefaultTheme() {
        viewModelScope.launch {
            getTheme(0L).collect {
                _defaultTheme.value = UIState.Success(it)
            }
        }
    }

    fun saveDefaultTheme(themeKey: String) {
        viewModelScope.launch {
            try {
                setTheme(0L, themeKey)
                _defaultTheme.value = UIState.Success(themeKey)
            } catch (t: Throwable) {
                _defaultTheme.value = UIState.Error("Kon standaardthema niet opslaan")
            }
        }
    }
}
