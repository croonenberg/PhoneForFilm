package com.example.phoneforfilm.data.repository

import com.example.phoneforfilm.data.local.dao.MessageDao
import com.example.phoneforfilm.data.model.Message
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MessageRepository @Inject constructor(
    private val messageDao: MessageDao
) {
    fun getMessagesByChatId(chatId: Int): Flow<List<Message>> {
        return messageDao.getAllMessagesForChat(chatId)
    }

    suspend fun insert(message: Message) {
        messageDao.insert(message)
    }

    suspend fun delete(message: Message) {
        messageDao.delete(message)
    }
}