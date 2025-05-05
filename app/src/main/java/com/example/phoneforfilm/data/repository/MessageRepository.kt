package com.example.phoneforfilm.data.repository

import androidx.lifecycle.LiveData
import com.example.phoneforfilm.data.local.dao.MessageDao
import com.example.phoneforfilm.data.model.Message
import javax.inject.Inject

class MessageRepository @Inject constructor(
    private val messageDao: MessageDao
) {
    fun getMessagesByChatId(chatId: Int): LiveData<List<Message>> {
        return messageDao.getMessagesByChatId(chatId)
    }

    suspend fun insert(message: Message) {
        messageDao.insert(message)
    }

    suspend fun delete(message: Message) {
        messageDao.delete(message)
    }
}