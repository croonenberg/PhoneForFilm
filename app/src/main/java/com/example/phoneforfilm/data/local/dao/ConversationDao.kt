package com.example.phoneforfilm.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.phoneforfilm.data.local.entity.Conversation
import kotlinx.coroutines.flow.Flow

@Dao
interface ConversationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(conversation: Conversation)

    @Query("SELECT * FROM conversations WHERE id = :id")
    fun getConversation(id: Int): Flow<Conversation?>

    @Query("SELECT * FROM conversations")
    fun getAllConversations(): Flow<List<Conversation>>
}
