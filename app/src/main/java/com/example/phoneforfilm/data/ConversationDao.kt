package com.example.phoneforfilm.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ConversationDao {
    @Query("SELECT * FROM conversations ORDER BY timestamp DESC")
    fun getAll(): LiveData<List<Conversation>>

    @Insert
    suspend fun insert(conversation: Conversation): Long

    @Delete
    suspend fun delete(conversation: Conversation)
}
