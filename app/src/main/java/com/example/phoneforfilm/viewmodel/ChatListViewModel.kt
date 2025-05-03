package com.example.phoneforfilm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phoneforfilm.data.repository.ConversationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChatListViewModel(
    private val repository: ConversationRepository
) : ViewModel() {

    fun createChatFor(contactId: Int, onComplete: (Int) -> Unit) {
        viewModelScope.launch {
            val chatId = repository.createForContact(contactId).toInt()
            withContext(Dispatchers.Main) {
                onComplete(chatId)
            }
        }
    }
}
