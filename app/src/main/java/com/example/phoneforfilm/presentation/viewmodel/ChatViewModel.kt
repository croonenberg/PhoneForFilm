package com.example.phoneforfilm.presentation.viewmodel

import androidx.lifecycle.*
import com.example.phoneforfilm.data.model.Message
import com.example.phoneforfilm.data.repository.MessageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val messageRepository: MessageRepository
) : ViewModel() {

    private val _chatId = MutableStateFlow<Int?>(null)

    val messages: StateFlow<List<Message>> = _chatId
        .filterNotNull()
        .flatMapLatest { chatId -> messageRepository.getMessagesByChatId(chatId) }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun loadMessagesForChat(chatId: Int) {
        _chatId.value = chatId
    }

    fun sendMessage(text: String, chatId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val message = Message(
                id = 0,
                chatId = chatId,
                text = text,
                timestamp = System.currentTimeMillis()
            )
            messageRepository.insert(message)
        }
    }

    fun deleteMessage(message: Message) {
        viewModelScope.launch(Dispatchers.IO) {
            messageRepository.delete(message)
        }
    }
}
