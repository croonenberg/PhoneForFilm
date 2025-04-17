package com.example.phoneforfilm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import com.example.phoneforfilm.data.Message
import com.example.phoneforfilm.data.MessageRepository
import kotlinx.coroutines.launch

class ChatViewModel(private val repo: MessageRepository) : ViewModel() {

    fun messages(contactId: Int): LiveData<List<Message>> = repo.getMessages(contactId)

    fun insert(msg: Message) = viewModelScope.launch { repo.insertMessage(msg) }

    fun update(msg: Message) = viewModelScope.launch { repo.updateMessage(msg) }

    fun delete(msg: Message) = viewModelScope.launch { repo.deleteMessage(msg) }
}
