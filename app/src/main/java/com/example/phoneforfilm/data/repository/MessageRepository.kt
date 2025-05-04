package com.example.phoneforfilm.data.repository

import androidx.lifecycle.LiveData
import com.example.phoneforfilm.data.local.dao.MessageDao
import com.example.phoneforfilm.data.model.Message
import javax.inject.Inject

class MessageRepository @Inject constructor(
    private val messageDao: MessageDao
) {
    suspend fun insertMessage(message: Message) = messageDao.insert(message)
    suspend fun updateMessage(message: Message) = messageDao.update(message)
    suspend fun deleteMessage(message: Message) = messageDao.delete(message)
    fun getMessagesForChat(chatId: Int): LiveData<List<Message>> = messageDao.getAllMessagesForChat(chatId)
}