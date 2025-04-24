
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

    fun loadMessages(chatId: Long) = viewModelScope.launch {
        val list = repository.getMessagesByChatId(chatId.toInt())
        _messages.postValue(list)
    }

    /**
     * Store a brand‑new outgoing message and refresh the list.
     */
    fun sendMessage(chatId: Long, text: String, senderId: Long) = viewModelScope.launch {
        val msg = Message(
            chatId = chatId.toInt(),
            senderId = senderId,
            text = text,
            timestamp = System.currentTimeMillis(),
            status = 0
        )
        repository.insert(msg)
        loadMessages(chatId)
    }

    fun updateMessage(message: Message) = viewModelScope.launch {
        repository.update(message)
        loadMessages(message.chatId.toLong())
    }

    fun deleteMessage(message: Message) = viewModelScope.launch {
        repository.delete(message)
        loadMessages(message.chatId.toLong())
    }
}
