package com.example.phoneforfilm.data

import androidx.room.*
import androidx.lifecycle.LiveData

@Dao
interface MessageDao {

    @Insert
    suspend fun insertMessage(message: Message)

    @Update
    suspend fun updateMessage(message: Message)

    @Delete
    suspend fun deleteMessage(message: Message)

    @Query("SELECT * FROM messages WHERE chatId = :chatId ORDER BY timestamp ASC")
    suspend fun getMessagesByChatId(chatId: Int): List<Message>

    @Query("SELECT * FROM messages WHERE chatId = :chatId ORDER BY timestamp ASC")
    fun getMessagesForChat(chatId: Int): LiveData<List<Message>>
}
