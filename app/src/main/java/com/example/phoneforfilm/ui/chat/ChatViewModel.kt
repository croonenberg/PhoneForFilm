
package com.example.phoneforfilm.ui.chat

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phoneforfilm.data.repository.MessageRepository
import com.example.phoneforfilm.domain.usecase.GetConversationThemeUseCase
import com.example.phoneforfilm.domain.usecase.SetConversationThemeUseCase
import com.example.phoneforfilm.ui.common.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val KEY_CONVERSATION_ID = "conversationId"

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val messageRepo: MessageRepository,
    private val getTheme: GetConversationThemeUseCase,
    private val setTheme: SetConversationThemeUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val conversationId: Long =
        requireNotNull(savedStateHandle.get<Long>(KEY_CONVERSATION_ID)) {
            "Conversation id is required"
        }

    val messages: Flow<List<com.example.phoneforfilm.data.local.entity.Message>> =
        messageRepo.getMessagesFor(conversationId.toInt())

    private val _themeState = MutableStateFlow<UIState<String?>>(UIState.Loading)
    val themeState: StateFlow<UIState<String?>> = _themeState.asStateFlow()

    init {
        viewModelScope.launch {
            getTheme(conversationId).collect { themeKey ->
                _themeState.value = UIState.Success(themeKey)
            }
        }
    }

    fun applyTheme(themeKey: String) {
        viewModelScope.launch {
            try {
                setTheme(conversationId, themeKey)
                _themeState.value = UIState.Success(themeKey)
            } catch (t: Throwable) {
                _themeState.value = UIState.Error(t.message ?: "Unknown error")
            }
        }
    }
}
