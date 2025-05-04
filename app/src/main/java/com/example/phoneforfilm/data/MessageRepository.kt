package com.example.phoneforfilm.data.repository

import com.example.phoneforfilm.data.dao.MessageDao
import com.example.phoneforfilm.data.model.Message
import javax.inject.Inject

/**
 * Repository for message-related data operations.
 */
class MessageRepository @Inject constructor(private val messageDao: MessageDao) {

    /**
     * Inserts a new message.
     */
    suspend fun insert(message: Message) {
        messageDao.insert(message)
    }

    /**
     * Updates an existing message.
     */
    suspend fun update(message: Message) {
        messageDao.update(message)
    }

    /**
     * Deletes a message.
     */
    suspend fun delete(message: Message) {
        messageDao.delete(message)
    }

    /**
     * Retrieves messages for a specific conversation.
     */
    fun getMessagesByChatId(chatId: Int) = messageDao.getMessagesForConversation(chatId)
}
