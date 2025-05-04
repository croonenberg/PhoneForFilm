package com.example.phoneforfilm.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.phoneforfilm.data.model.Message

@Dao
interface MessageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(message: Message)

    @Delete
    suspend fun delete(message: Message)

    @Query("SELECT * FROM messages WHERE chatId = :chatId ORDER BY timestamp ASC")
    fun getAllMessagesForChat(chatId: Int): LiveData<List<Message>>
}