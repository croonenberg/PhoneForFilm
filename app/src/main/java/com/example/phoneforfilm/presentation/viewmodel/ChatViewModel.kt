package com.example.phoneforfilm.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phoneforfilm.data.model.Message
import com.example.phoneforfilm.data.repository.MessageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val messageRepository: MessageRepository
) : ViewModel() {

    val messages: LiveData<List<Message>> = messageRepository.getAllMessages()

    fun loadMessagesForChat(chatId: Int) {
        viewModelScope.launch {
            messageRepository.getMessagesByChatId(chatId)
        }
    }

    fun sendMessage(text: String, chatId: Int, senderId: Int) {
        viewModelScope.launch {
            val message = Message(text = text, chatId = chatId, senderId = senderId)
            messageRepository.insert(message)
        }
    }
}
