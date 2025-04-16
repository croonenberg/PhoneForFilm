package com.example.phoneforfilm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phoneforfilm.data.Message
import com.example.phoneforfilm.data.MessageRepository
import kotlinx.coroutines.launch

class ChatViewModel(private val repository: MessageRepository) : ViewModel() {

    val allMessages: LiveData<List<Message>> = repository.getAllMessages()

    fun insertMessage(message: Message) {
        viewModelScope.launch {
            repository.insert(message)
        }
    }
}
