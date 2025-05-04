package com.example.phoneforfilm.data.repository

import androidx.lifecycle.LiveData
import com.example.phoneforfilm.data.model.Message
import com.example.phoneforfilm.data.room.MessageDao

class MessageRepository(private val messageDao: MessageDao) {

    suspend fun insert(message: Message) {
        messageDao.insert(message)
    }

    suspend fun delete(message: Message) {
        messageDao.delete(message)
    }

    fun getAllMessagesForChat(chatId: Int): LiveData<List<Message>> {
        return messageDao.getAllMessagesForChat(chatId)
    }
}