package com.example.phoneforfilm.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _messages = MutableLiveData<List<Message>>()
    val messages: LiveData<List<Message>> = _messages

    fun loadMessagesForChat(conversationId: Int) {
        viewModelScope.launch {
            messageRepository.getMessagesByChatId(conversationId).collect { msgs ->
                _messages.value = msgs
            }
        }
    }

    fun sendMessage(text: String, conversationId: Int, senderId: Int) {
        val message = Message(
            id = 0,
            conversationId = conversationId,
            senderId = senderId,
            text = text,
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
