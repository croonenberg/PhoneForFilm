package com.example.phoneforfilm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phoneforfilm.data.model.Message
import com.example.phoneforfilm.data.dao.MessageDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val messageDao: MessageDao
) : ViewModel() {

    // Observe all messages (could filter by conversationId if needed)
    val messages: LiveData<List<Message>> = messageDao.getAllMessages()

    /**
     * Load messages for a specific conversation if filtering is required.
     */
    fun loadMessages(chatId: Int) {
        // existing placeholder
        viewModelScope.launch(Dispatchers.IO) {
            // implement filtering if using DAO method getMessagesForConversation
        }
    }

    /**
     * Insert a new message into the database.
     */
    fun sendMessage(message: Message) {
        viewModelScope.launch(Dispatchers.IO) {
            messageDao.insert(message)
        }
    }
}
