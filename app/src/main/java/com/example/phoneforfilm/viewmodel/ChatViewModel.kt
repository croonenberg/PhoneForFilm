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

    fun getMessages(conversationId: Int): LiveData<List<Message>> =
        messageRepository.getMessagesForConversation(conversationId)

    fun sendMessage(msg: Message) {
        viewModelScope.launch {
            messageRepository.insertMessage(msg)
        }
    }

    fun updateMessage(msg: Message) {
        viewModelScope.launch {
            messageRepository.updateMessage(msg)
        }
    }

    fun deleteMessage(msg: Message) {
        viewModelScope.launch {
            messageRepository.deleteMessage(msg)
        }
    }
}
