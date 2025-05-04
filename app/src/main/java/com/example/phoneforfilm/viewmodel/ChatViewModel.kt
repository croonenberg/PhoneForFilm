package com.example.phoneforfilm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.phoneforfilm.data.model.Message
import com.example.phoneforfilm.data.repository.MessageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.phoneforfilm.di.IoDispatcher
import com.example.phoneforfilm.di.MainDispatcher

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val repo: MessageRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val conversationIdFlow = MutableStateFlow(-1)

    /** LiveData van gesorteerde berichten voor huidige chat */
    val messages: LiveData<List<Message>> = conversationIdFlow
        .flatMapLatest { chatId -> repo.getMessagesByChatId(chatId) }
        .map { messageList -> messageList.sortedBy { it.timestamp } }
        .asLiveData(context = viewModelScope.coroutineContext + mainDispatcher)

    /** Stel in welke chat we nu tonen */
    fun startConversation(id: Int) {
        conversationIdFlow.value = id
    }

    /** Verstuur nieuw bericht met tekst */
    fun sendMessage(text: String) {
        val msg = Message(
            conversationId = conversationIdFlow.value,
            text = text,
            timestamp = System.currentTimeMillis(),
            isSender = true
        )
        viewModelScope.launch(ioDispatcher) {
            repo.insert(msg)
        }
    }

    /** Update bestaand bericht */
    fun updateMessage(msg: Message) {
        viewModelScope.launch(ioDispatcher) { repo.update(msg) }
    }

    /** Verwijder bericht */
    fun deleteMessage(msg: Message) {
        viewModelScope.launch(ioDispatcher) { repo.delete(msg) }
    }
}
