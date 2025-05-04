package com.example.phoneforfilm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.phoneforfilm.data.dao.MessageDao
import com.example.phoneforfilm.viewmodel.ChatViewModel

/**
 * Factory for creating ChatViewModel with a MessageDao dependency.
 */
class ChatViewModelFactory(
    private val messageDao: MessageDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChatViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ChatViewModel(messageDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}