package com.example.phoneforfilm.viewmodel

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

    // expose LiveData directly
    fun getMessages(conversationId: Int): LiveData<List<Message>> =
        messageRepository.getMessagesByChatId(conversationId)

    fun sendMessage(message: Message) {
        viewModelScope.launch {
            messageRepository.insert(message)
        }
    }

    fun updateMessage(message: Message) {
        viewModelScope.launch {
            messageRepository.update(message)
        }
    }

    fun deleteMessage(message: Message) {
        viewModelScope.launch {
            messageRepository.delete(message)
        }
    }
}
