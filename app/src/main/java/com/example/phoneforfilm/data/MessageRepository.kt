package com.example.phoneforfilm.data

import javax.inject.Inject


package com.example.phoneforfilm.data

class MessageRepository @Inject constructor(private val messageDao: MessageDao) {

    suspend fun insert(message: Message) {
        messageDao.insertMessage(message)
    }

    suspend fun update(message: Message) {
        messageDao.updateMessage(message)
    }

    suspend fun delete(message: Message) {
        messageDao.deleteMessage(message)
    }

    suspend fun getMessagesByChatId(chatId: Int): List<Message> {
        return messageDao.getMessagesByChatId(chatId)
    }
}
