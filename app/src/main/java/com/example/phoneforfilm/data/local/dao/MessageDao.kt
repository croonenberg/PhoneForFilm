package com.example.phoneforfilm.data.local.dao

import androidx.room.*
import com.example.phoneforfilm.data.model.Message
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {

    @Query("SELECT * FROM messages WHERE conversationId = :chatId ORDER BY timestamp ASC")
    fun getMessagesByChatId(chatId: Int): Flow<List<Message>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(message: Message)

    @Delete
    suspend fun delete(message: Message)
}
