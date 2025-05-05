package com.example.phoneforfilm.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phoneforfilm.data.model.Message
import com.example.phoneforfilm.data.repository.MessageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val messageRepository: MessageRepository
) : ViewModel() {

    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages: StateFlow<List<Message>> = _messages.asStateFlow()

    fun loadMessagesForChat(chatId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            messageRepository.getMessagesByChatId(chatId).collectLatest { msgs: List<Message> ->
                _messages.value = msgs
            }
        }
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
