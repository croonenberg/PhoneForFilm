
package com.example.phoneforfilm.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ConversationDao {

    @Query("SELECT * FROM conversations ORDER BY timestamp DESC")
    fun getAll(): LiveData<List<Conversation>>

    @Query("SELECT * FROM conversations ORDER BY timestamp DESC")
    fun getAllNow(): List<Conversation>

    @Query("SELECT id FROM conversations WHERE contactId = :contactId LIMIT 1")
    fun getIdByContact(contactId: Int): Int?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(conversation: Conversation): Long

    @Update
    suspend fun update(conversation: Conversation)

    @Delete
    suspend fun delete(conversation: Conversation)
}
