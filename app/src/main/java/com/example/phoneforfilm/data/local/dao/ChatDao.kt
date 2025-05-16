package com.example.phoneforfilm.data.local.dao
@file:Suppress("unused", "UnusedImport")

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.phoneforfilm.data.model.Chat

@Dao
interface ChatDao {
    @Query("SELECT * FROM chats WHERE id = :id")
    fun getChatById(id: Int): LiveData<Chat>

    @Query("SELECT * FROM chats")
    fun getAllChats(): LiveData<List<Chat>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(chat: Chat)
}