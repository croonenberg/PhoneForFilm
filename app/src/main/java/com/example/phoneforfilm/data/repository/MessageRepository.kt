package com.example.phoneforfilm.data.repository

import com.example.phoneforfilm.data.local.dao.MessageDao
import com.example.phoneforfilm.data.local.entity.Message
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MessageRepository @Inject constructor(
    private val messageDao: MessageDao
) {
    suspend fun sendMessage(message: Message) {
        messageDao.insert(message)
    }

    fun getMessages(conversationId: Int): Flow<List<Message>> {
        return messageDao.getMessagesForConversation(conversationId)
    }
}
