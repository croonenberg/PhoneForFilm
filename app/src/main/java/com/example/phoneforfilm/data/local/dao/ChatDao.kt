package com.example.phoneforfilm.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.phoneforfilm.data.model.Chat

@Dao
interface ChatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(chat: Chat): Long

    @Update
    suspend fun update(chat: Chat)

    @Delete
    suspend fun delete(chat: Chat)

    @Query("SELECT * FROM chats ORDER BY id ASC")
    fun getAllChats(): LiveData<List<Chat>>

    @Query("SELECT * FROM chats WHERE id = :chatId LIMIT 1")
    suspend fun getChatById(chatId: Int): Chat?
}