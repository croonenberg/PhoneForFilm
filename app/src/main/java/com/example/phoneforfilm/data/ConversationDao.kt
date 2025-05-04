package com.example.phoneforfilm.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.phoneforfilm.data.Conversation
import kotlinx.coroutines.flow.Flow

@Dao
interface ConversationDao {
    @Insert
    suspend fun insert(conversation: Conversation): Long

    @Update
    suspend fun update(conversation: Conversation)

    @Query("SELECT conv.id, conv.contactId, conv.lastMessage, conv.timestamp, " +
           "c.name AS contactName FROM conversations conv " +
           "JOIN contacts c ON conv.contactId = c.id " +
           "ORDER BY conv.timestamp DESC")
    fun getAll(): Flow<List<Conversation>>

    @Query("SELECT * FROM conversations WHERE id = :id")
    suspend fun getById(id: Int): Conversation
}
