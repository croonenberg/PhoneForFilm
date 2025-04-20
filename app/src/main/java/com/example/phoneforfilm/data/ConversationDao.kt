package com.example.phoneforfilm.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ConversationDao {

    @Query("SELECT * FROM conversations ORDER BY timestamp DESC")
    fun getAll(): LiveData<List<Conversation>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(conversation: Conversation): Long

    @Update
    suspend fun update(conversation: Conversation)

    @Delete
    suspend fun delete(conversation: Conversation)
}
