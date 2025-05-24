package com.example.phoneforfilm.ui.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phoneforfilm.domain.usecase.GetConversationThemeUseCase
import com.example.phoneforfilm.domain.usecase.SetConversationThemeUseCase
import com.example.phoneforfilm.ui.common.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getTheme: GetConversationThemeUseCase,
    private val setTheme: SetConversationThemeUseCase
) : ViewModel() {

    private val _themeState = MutableStateFlow<UIState<String?>>(UIState.Loading)
    val themeState: StateFlow<UIState<String?>> = _themeState

    fun loadTheme(conversationId: Long) {
        viewModelScope.launch {
            getTheme(conversationId).collect { key ->
                _themeState.value = UIState.Success(key)
            }
        }
    }

    fun applyTheme(conversationId: Long, themeKey: String) {
        viewModelScope.launch {
            try {
                setTheme(conversationId, themeKey)
                _themeState.value = UIState.Success(themeKey)
            } catch (t: Throwable) {
                _themeState.value = UIState.Error("Kon thema niet instellen")
            }
        }
    }
}
