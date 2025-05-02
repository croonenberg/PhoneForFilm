package com.example.phoneforfilm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.phoneforfilm.data.Message
import com.example.phoneforfilm.data.MessageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val repository: MessageRepository
) : ViewModel() {

    private val _messages = MutableLiveData<List<Message>>()
    val messages: LiveData<List<Message>> = _messages

    fun loadMessages(chatId: Int) = viewModelScope.launch {
        _messages.value = repository.getMessagesByChatId(chatId)
    }

    fun sendMessage(msg: Message) = viewModelScope.launch {
        repository.insert(msg)
        loadMessages(msg.chatId.toInt())
    }

    fun updateMessage(message: Message) = viewModelScope.launch {
        repository.update(message)
        loadMessages(message.chatId.toInt())
    }

    fun deleteMessage(message: Message) = viewModelScope.launch {
        repository.delete(message)
        loadMessages(message.chatId.toInt())
    }
}
