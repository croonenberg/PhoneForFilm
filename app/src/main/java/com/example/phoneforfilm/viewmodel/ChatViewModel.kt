package com.example.phoneforfilm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phoneforfilm.data.model.Message
import com.example.phoneforfilm.data.repository.MessageRepository
import com.example.phoneforfilm.data.repository.ConversationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val messageRepository: MessageRepository,
    private val conversationRepository: ConversationRepository
) : ViewModel() {

    private val _messages = MutableLiveData<List<Message>>()
    val messages: LiveData<List<Message>> = _messages

    fun loadMessages(conversationId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            messageRepository.getForConversation(conversationId)
                .collect { msgs -> _messages.postValue(msgs) }
        }
    }

    fun updateMessage(message: Message) {
        viewModelScope.launch(Dispatchers.IO) {
            messageRepository.update(message)
        }
    }

    fun deleteMessage(message: Message) {
        viewModelScope.launch(Dispatchers.IO) {
            messageRepository.delete(message)
        }
    }

    fun setThemeForConversation(conversationId: Long, theme: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val conv = conversationRepository.getById(conversationId)
            conversationRepository.update(conv.copy(theme = theme))
        }
    }

    fun setLanguageForConversation(conversationId: Long, languageCode: String) {
        // For simplicity, store language in conversation meta
        viewModelScope.launch(Dispatchers.IO) {
            val conv = conversationRepository.getById(conversationId)
            // Assuming Conversation has a language field; if not, adapt accordingly
            // conversationRepository.update(conv.copy(language = languageCode))
        }
    }
}
