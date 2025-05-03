package com.example.phoneforfilm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.phoneforfilm.data.Conversation
import com.example.phoneforfilm.data.ConversationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatListViewModel @Inject constructor(
    repository: ConversationRepository
) : ViewModel() {

    val conversations: LiveData<List<Conversation>> = repository.allConversations
}