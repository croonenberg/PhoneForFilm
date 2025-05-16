package com.example.phoneforfilm.data.repository

import com.example.phoneforfilm.data.local.dao.ConversationDao
import com.example.phoneforfilm.data.local.entity.Conversation
import javax.inject.Inject

class ConversationRepository @Inject constructor(
    private val dao: ConversationDao
) {
    /**
     * Maak nieuw gesprek aan en retourneer de ID.
     */
    suspend fun create(conversation: Conversation): Long =
        dao.insert(conversation)

    /**
     * Haal alle gesprekken op als Flow.
     */
    fun getAll() = dao.getAll()

    /**
     * Update een bestaand gesprek.
     */
    suspend fun update(conversation: Conversation) = dao.update(conversation)

    /**
     * Haal één gesprek op op basis van ID.
     */
    suspend fun getById(id: Int): Conversation? = dao.getById(id)
}
