package com.example.phoneforfilm.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Delete
import com.example.phoneforfilm.data.model.Message

/**
 * DAO for accessing Message entities.
 */
@Dao
interface MessageDao {

    /**
     * Retrieve all messages across all conversations.
     */
    @Query("SELECT * FROM messages ORDER BY timestamp ASC")
    fun getAllMessages(): LiveData<List<Message>>

    /**
     * Retrieve messages for a specific conversation.
     */
    @Query("SELECT * FROM messages WHERE conversationId = :conversationId ORDER BY timestamp ASC")
    fun getMessagesForConversation(conversationId: Int): LiveData<List<Message>>

    /**
     * Insert a new message and return the newly generated ID.
     */
    @Insert
    suspend fun insert(message: Message): Long

    /**
     * Update an existing message.
     */
    @Update
    suspend fun update(message: Message)

    /**
     * Delete a message.
     */
    @Delete
    suspend fun delete(message: Message)
}
