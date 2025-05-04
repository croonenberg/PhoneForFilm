package com.example.phoneforfilm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.phoneforfilm.data.repository.MessageRepository
import com.example.phoneforfilm.data.repository.ConversationRepository

/**
 * Factory for creating ChatViewModel with its repository dependencies.
 */
class ChatViewModelFactory(
    private val messageRepository: MessageRepository,
    private val conversationRepository: ConversationRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChatViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ChatViewModel(messageRepository, conversationRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
