package com.example.phoneforfilm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phoneforfilm.data.model.Message
import com.example.phoneforfilm.data.repository.MessageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val messageRepository: MessageRepository
) : ViewModel() {

    private val _messages = MutableLiveData<List<Message>>()
    val messages: LiveData<List<Message>> = _messages

    fun loadMessages(conversationId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            messageRepository.getMessagesByChatId(conversationId)
                .collect { msgs -> _messages.postValue(msgs) }
        }
    }

    fun updateMessage(message: Message) {
        viewModelScope.launch(Dispatchers.IO) {
            messageRepository.updateMessage(message)
        }
    }

    fun deleteMessage(message: Message) {
        viewModelScope.launch(Dispatchers.IO) {
            messageRepository.deleteMessage(message)
        }
    }
}
