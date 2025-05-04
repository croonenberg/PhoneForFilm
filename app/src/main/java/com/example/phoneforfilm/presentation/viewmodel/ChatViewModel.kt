package com.example.phoneforfilm.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phoneforfilm.data.model.Message
import com.example.phoneforfilm.data.repository.MessageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val messageRepository: MessageRepository
) : ViewModel() {

    fun getMessagesByChatId(chatId: Int): Flow<List<Message>> {
        return messageRepository.getMessagesByChatId(chatId)
    }

    fun sendMessage(chatId: Int, text: String) {
        val message = Message(
            id = 0,
            chatId = chatId,
            text = text,
            isSender = true,
            timestamp = System.currentTimeMillis()
        )
        viewModelScope.launch {
            messageRepository.insert(message)
        }
    }

    fun deleteMessage(message: Message) {
        viewModelScope.launch {
            messageRepository.delete(message)
        }
    }
}