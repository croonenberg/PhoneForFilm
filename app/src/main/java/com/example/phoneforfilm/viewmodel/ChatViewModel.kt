package com.example.phoneforfilm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
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

    // Gesorteerde stroom berichten
    val messagesFlow: StateFlow<List<Message>> = repo
        .getMessagesByChatId(conversationId)
        .map { list -> list.sortedBy { msg -> msg.timestamp } }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun getMessages(chatId: Int): LiveData<List<Message>> =
        repo.getMessagesByChatId(chatId).asLiveData()

    fun startConversation(id: Int) {
        conversationId = id
    }

    fun sendMessage(text: String) {
        val msg = Message(conversationId = conversationId, text = text, timestamp = System.currentTimeMillis(), isSender = true)
        viewModelScope.launch(ioDispatcher) {
            repo.insert(msg)
        }
    }

    fun updateMessage(msg: Message) {
        viewModelScope.launch(ioDispatcher) { repo.update(msg) }
    }

    fun deleteMessage(msg: Message) {
        viewModelScope.launch(ioDispatcher) { repo.delete(msg) }
    }