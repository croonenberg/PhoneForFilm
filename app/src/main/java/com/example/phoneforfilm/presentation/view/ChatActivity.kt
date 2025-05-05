package com.example.phoneforfilm.presentation.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.asLiveData
import com.example.phoneforfilm.data.model.Message
import com.example.phoneforfilm.data.repository.MessageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val messageRepository: MessageRepository
) : ViewModel() {

    fun getMessages(chatId: Int): LiveData<List<Message>> {
        return messageRepository.getMessagesByChatId(chatId).asLiveData()
    }

    fun sendMessage(text: String, chatId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val message = Message(
                id = 0,
                chatId = chatId,
                text = text,
                timestamp = System.currentTimeMillis()
            )
            messageRepository.insert(message)
        }
    }

    fun deleteMessage(message: Message) {
        viewModelScope.launch(Dispatchers.IO) {
            messageRepository.delete(message)
        }
    }
}

annotation class ChatActivity
