
package com.example.phoneforfilm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phoneforfilm.data.Message
import com.example.phoneforfilm.data.MessageRepository
import kotlinx.coroutines.launch

class ChatViewModel(private val repository: MessageRepository) : ViewModel() {

    private val _messages = MutableLiveData<List<Message>>()
    val messages: LiveData<List<Message>> get() = _messages

    fun loadMessages(chatId: Int) {
        viewModelScope.launch {
            _messages.value = repository.getMessagesByChatId(chatId)
        }
    }

    fun sendMessage(chatId: Int, content: String) {
        val message = Message(chatId = chatId, content = content, isSent = true, timestamp = System.currentTimeMillis())
        insertMessage(message)
    }

    fun insertMessage(message: Message) {
        viewModelScope.launch {
            repository.insert(message)
            loadMessages(message.chatId)
        }
    }

    fun updateMessage(message: Message) {
        viewModelScope.launch {
            repository.update(message)
            loadMessages(message.chatId)
        }
    }

    fun deleteMessage(message: Message) {
        viewModelScope.launch {
            repository.delete(message)
            loadMessages(message.chatId)
        }
    }
}
