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

    /**
     * Load messages for the given chatId from repository and post to LiveData.
     */
    fun loadMessages(chatId: Long) = viewModelScope.launch {
        val list = repository.getMessagesByChatId(chatId.toInt())
        _messages.postValue(list)
    }

    /**
     * Send a new message and reload messages.
     */
    fun sendMessage(chatId: Long, text: String, senderId: Long) = viewModelScope.launch {
        val msg = Message(
            chatId = chatId.toInt(),
            text = text,
            senderId = senderId,
            timestamp = System.currentTimeMillis(),
            status = 0
        )
        repository.insert(msg)
        loadMessages(chatId)
    }

    /**
     * Update an existing message and reload.
     */
    fun updateMessage(message: Message) = viewModelScope.launch {
        repository.update(message)
        loadMessages(message.chatId.toLong())
    }

    /**
     * Delete a message and reload.
     */
    fun deleteMessage(message: Message) = viewModelScope.launch {
        repository.delete(message)
        loadMessages(message.chatId.toLong())
    }
}
