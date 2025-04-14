package com.example.phoneforfilm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.phoneforfilm.data.AppDatabase
import com.example.phoneforfilm.data.Message
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChatViewModel(application: Application) : AndroidViewModel(application) {

    private val messageDao = AppDatabase.getDatabase(application).messageDao()

    fun insertMessage(message: Message) {
        viewModelScope.launch(Dispatchers.IO) {
            messageDao.insert(message)
        }
    }

    fun getMessagesByContact(contactId: Int): LiveData<List<Message>> {
        return messageDao.getMessagesByContact(contactId)
    }

    fun deleteMessage(message: Message) {
        viewModelScope.launch(Dispatchers.IO) {
            messageDao.delete(message)
        }
    }

    fun updateMessage(message: Message) {
        viewModelScope.launch(Dispatchers.IO) {
            messageDao.update(message)
        }
    }
}
