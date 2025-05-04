package com.example.phoneforfilm.data.repository

import androidx.lifecycle.LiveData
import com.example.phoneforfilm.data.local.dao.MessageDao
import com.example.phoneforfilm.data.model.Message
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MessageRepository @Inject constructor(
    private val messageDao: MessageDao
) {
    fun getMessagesForChat(chatId: Int): LiveData<List<Message>> {
        return messageDao.getAllMessagesForChat(chatId)
    }

    suspend fun insert(message: Message) {
        messageDao.insert(message)
    }

    suspend fun delete(message: Message) {
        messageDao.delete(message)
    }

    suspend fun update(message: Message) {
        messageDao.update(message)
    }
}