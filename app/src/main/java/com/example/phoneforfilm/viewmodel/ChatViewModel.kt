package com.example.phoneforfilm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.phoneforfilm.data.model.Message
import com.example.phoneforfilm.data.repository.MessageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val repo: MessageRepository
) : ViewModel() {

    private val conversationIdFlow = MutableStateFlow(-1)

    /** LiveData met berichten voor de actieve chat */
    val messages: LiveData<List<Message>> = conversationIdFlow
        .asLiveData()
        .switchMap { chatId -> repo.getMessagesByChatId(chatId) }

    fun startConversation(id: Int) {
        conversationIdFlow.value = id
    }

    fun sendMessage(text: String) {
        val msg = Message(
            conversationId = conversationIdFlow.value,
            text = text,
            timestamp = System.currentTimeMillis(),
            isSender = true
        )
        viewModelScope.launch {
            repo.insert(msg)
        }
    }

    fun updateMessage(msg: Message) {
        viewModelScope.launch { repo.update(msg) }
    }

    fun deleteMessage(msg: Message) {
        viewModelScope.launch { repo.delete(msg) }
    }
}
