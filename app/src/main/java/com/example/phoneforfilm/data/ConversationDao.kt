package com.example.phoneforfilm.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert

@Dao
interface ConversationDao {

    @Insert
    suspend fun insert(conversation: Conversation): Long

    @Delete
    suspend fun delete(conversation: Conversation)
}
