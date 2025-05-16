package com.example.phoneforfilm.data.repository
@file:Suppress("unused", "UnusedImport")

import com.example.phoneforfilm.data.local.dao.MessageDao
import com.example.phoneforfilm.data.local.entity.Message
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MessageRepository @Inject constructor(
    private val dao: MessageDao
) {
    /**
     * Returns all messages for a conversation as a Flow, ordered by timestamp ascending.
     */
    fun getMessagesFor(convId: Int): Flow<List<Message>> = dao.getMessages(convId)

    /**
     * Inserts a new message and returns its generated ID.
     */
    suspend fun send(message: Message): Long = dao.insert(message)

    /**
     * Updates an existing message.
     */
    suspend fun update(message: Message) = dao.update(message)

    /**
     * Deletes the given message.
     */
    suspend fun delete(message: Message) = dao.delete(message)
}
