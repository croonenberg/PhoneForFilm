package com.example.phoneforfilm.data.repository

import com.example.phoneforfilm.data.Conversation
import com.example.phoneforfilm.data.ConversationDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ConversationRepository @Inject constructor(
    private val dao: ConversationDao
) {
    suspend fun create(conversation: Conversation) = dao.insert(conversation)
    fun getAll(): Flow<List<Conversation>> = dao.getAll()
    suspend fun update(conversation: Conversation) = dao.update(conversation)
    suspend fun getById(id: Int) = dao.getById(id)
}
