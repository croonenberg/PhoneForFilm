package com.example.phoneforfilm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phoneforfilm.data.repository.ConversationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatListViewModel @Inject constructor(
    private val repository: ConversationRepository
) : ViewModel() {

    val conversations: LiveData<List<com.example.phoneforfilm.data.Conversation>> =
        repository.getAll()

    fun createChatFor(contactId: Int) {
        viewModelScope.launch {
            repository.createForContact(contactId)
        }
    }
}
