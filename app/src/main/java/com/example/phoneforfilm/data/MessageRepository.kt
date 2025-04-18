package com.example.phoneforfilm.data

class MessageRepository(private val dao: MessageDao) {

    fun getMessages(contactId: Int) = dao.getMessagesForContact(contactId)

    suspend fun insertMessage(message: Message) = dao.insert(message)

    suspend fun updateMessage(message: Message) = dao.update(message)

    suspend fun deleteMessage(message: Message) = dao.delete(message)
}

