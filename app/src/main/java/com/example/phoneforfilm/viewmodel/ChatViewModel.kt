package com.example.phoneforfilm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phoneforfilm.data.model.Message
import com.example.phoneforfilm.data.repository.MessageRepository
import com.example.phoneforfilm.di.IoDispatcher
import com.example.phoneforfilm.di.MainDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val repo: MessageRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher
) : ViewModel() {

    private var conversationId: Int = -1

    val messages: StateFlow<List<Message>> = repo
        .getForConversationFlow(conversationId)
        .map { list -> list.sortedBy { it.timestamp } }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun startConversation(id: Int) {
        conversationId = id
    }

    fun sendMessage(text: String) {
        val msg = Message(
            conversationId = conversationId,
            text = text,
            timestamp = System.currentTimeMillis(),
            isSender = true
        )
        viewModelScope.launch(ioDispatcher) {
            repo.insertMessage(msg)
        }
    }

    fun updateMessage(msg: Message) {
        viewModelScope.launch(ioDispatcher) {
            repo.updateMessage(msg)
        }
    }

    fun deleteMessage(msg: Message) {
        viewModelScope.launch(ioDispatcher) {
            repo.deleteMessage(msg)
        }
    }
}
