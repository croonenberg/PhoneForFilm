package com.example.phoneforfilm.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phoneforfilm.data.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository
) : ViewModel() {

    fun createChatFor(contactId: Int, onChatCreated: (Int) -> Unit) {
        viewModelScope.launch {
            val conversationId = chatRepository.createConversation(contactId)
            onChatCreated(conversationId)
        }
    }
}
