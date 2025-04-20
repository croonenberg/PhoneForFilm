package com.example.phoneforfilm.data

import androidx.lifecycle.LiveData

class ConversationRepository(private val dao: ConversationDao) {

    fun getAll(): LiveData<List<Conversation>> = dao.getAll()

    suspend fun insert(conversation: Conversation): Long = dao.insert(conversation)

    suspend fun update(conversation: Conversation) = dao.update(conversation)

    suspend fun delete(conversation: Conversation) = dao.delete(conversation)
}
