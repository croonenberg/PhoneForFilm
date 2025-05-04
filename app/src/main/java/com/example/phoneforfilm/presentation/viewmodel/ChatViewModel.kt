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
    private val repository: MessageRepository
) : ViewModel() {

    val messages: LiveData<List<Message>> = repository.getAllMessagesForChat(0)

    fun sendMessage(text: String) {
        val message = Message(
            id = 0,
            chatId = 0,
            text = text,
            timestamp = System.currentTimeMillis(),
            isSender = true
        )
        viewModelScope.launch {
            repository.insert(message)
        }
    }

    fun deleteMessage(message: Message) {
        viewModelScope.launch {
            repository.delete(message)
        }
    }
}