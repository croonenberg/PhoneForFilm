package com.example.phoneforfilm.ui.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phoneforfilm.data.ChatTheme
import com.example.phoneforfilm.data.ChatThemeDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChatViewModel(private val chatThemeDao: ChatThemeDao) : ViewModel() {
    private val _themeFlow = MutableStateFlow<String?>(null)
    val themeFlow: StateFlow<String?> = _themeFlow

    fun loadTheme(conversationId: Long) {
        viewModelScope.launch {
            _themeFlow.value = chatThemeDao.getTheme(conversationId)
        }
    }

    fun setTheme(conversationId: Long, themeKey: String) {
        viewModelScope.launch {
            chatThemeDao.saveTheme(ChatTheme(conversationId, themeKey))
            _themeFlow.value = themeKey
        }
    }
}
