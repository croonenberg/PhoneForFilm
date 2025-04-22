package com.example.phoneforfilm.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ConversationDao {
    @Query("SELECT * FROM conversations")
    fun getAllConversations(): List<Conversation>

    @Insert
    fun insert(conversation: Conversation)

    @Query("DELETE FROM conversations")
    fun clearAll()
}
