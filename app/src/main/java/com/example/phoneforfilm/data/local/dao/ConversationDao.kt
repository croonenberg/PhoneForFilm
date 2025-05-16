package com.example.phoneforfilm.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.phoneforfilm.data.local.entity.Conversation
import kotlinx.coroutines.flow.Flow

@Dao
interface ConversationDao {
    /**
     * Maak nieuw gesprek aan en retourneer de gegenereerde ID.
     */
    @Insert
    suspend fun insert(conversation: Conversation): Long

    /**
     * Haal alle gesprekken op.
     */
    @Query("SELECT * FROM conversations")
    fun getAll(): Flow<List<Conversation>>

    /**
     * Update een bestaand gesprek.
     */
    @Update
    suspend fun update(conversation: Conversation)

    /**
     * Haal één gesprek op op basis van ID.
     */
    @Query("SELECT * FROM conversations WHERE id = :id LIMIT 1")
    suspend fun getById(id: Int): Conversation?

    /**
     * Vind gesprek-id op basis van contactId.
     */
    @Query("SELECT id FROM conversations WHERE contactId = :contactId LIMIT 1")
    suspend fun findByContact(contactId: Int): Int?
}
