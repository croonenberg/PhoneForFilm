package com.example.phoneforfilm.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ConversationDao {
    @Insert
    suspend fun insert(conversation: Conversation): Long

    @Query("SELECT * FROM conversations")
    suspend fun getAll(): List<Conversation>

    @Update
    suspend fun update(conversation: Conversation)

    @Query("SELECT * FROM conversations WHERE id = :id")
    suspend fun getById(id: Int): Conversation
}
