package com.example.phoneforfilm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phoneforfilm.data.Conversation
import com.example.phoneforfilm.data.ConversationRepository
import kotlinx.coroutines.launch

class ChatListViewModel(private val repository: ConversationRepository) : ViewModel() {
    val allConversations: LiveData<List<Conversation>> = repository.allConversations

    fun deleteConversation(conversation: Conversation) = viewModelScope.launch {
        repository.delete(conversation)
    }
}
