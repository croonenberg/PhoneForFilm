package com.example.phoneforfilm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phoneforfilm.data.Message
import com.example.phoneforfilm.data.repository.MessageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import androidx.lifecycle.MutableLiveData

@HiltViewModel
class ChatListViewModel @Inject constructor(
    private val repository: MessageRepository
) : ViewModel() {

    private val _conversations = MutableLiveData<List<Message>>()
    val conversations: LiveData<List<Message>> = _conversations

    fun loadConversations() {
        viewModelScope.launch {
            _conversations.value = repository.getLatestMessagesPerChat()
        }
    }
}