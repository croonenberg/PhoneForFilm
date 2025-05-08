package com.example.phoneforfilm.data.local.dao

import androidx.room.*
import com.example.phoneforfilm.data.local.entity.Conversation

@Dao
interface ConversationDao {
    /**
     * Maak nieuw gesprek aan.
     */
    @Insert
    suspend fun insert(conversation: Conversation)

    /**
     * Vind gesprek-id op basis van contactId.
     */
    @Query("SELECT id FROM conversations WHERE contactId = :contactId LIMIT 1")
    suspend fun findByContact(contactId: Int): Int?
}
