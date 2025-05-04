package com.example.phoneforfilm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.phoneforfilm.data.Conversation
import com.example.phoneforfilm.data.repository.ConversationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatListViewModel @Inject constructor(
    private val conversationRepository: ConversationRepository
) : ViewModel() {

    val conversations: LiveData<List<Conversation>> =
        conversationRepository.getAll().asLiveData()

    fun createFor(binding: ActivityChatListBinding) {
        viewModelScope.launch {
            val convId = conversationRepository.createForContact(selectedContactId)
            start ChatActivity with Intent...
        }
    }
}
