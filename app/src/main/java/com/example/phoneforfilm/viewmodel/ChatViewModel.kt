package com.example.phoneforfilm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phoneforfilm.data.model.Message
import com.example.phoneforfilm.data.dao.MessageDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val messageDao: MessageDao
) : ViewModel() {

    val messages: LiveData<List<Message>> = messageDao.getAllMessages()

    fun loadMessages(chatId: Int) {
        // existing logic or placeholder
    }

    fun sendMessage(message: Message) {
        viewModelScope.launch(Dispatchers.IO) {
            messageDao.insert(message)
        }
    }

    fun updateMessage(message: Message) {
        viewModelScope.launch(Dispatchers.IO) {
            messageDao.update(message)
        }
    }

    fun deleteMessage(message: Message) {
        viewModelScope.launch(Dispatchers.IO) {
            messageDao.delete(message)
        }
    }
}
