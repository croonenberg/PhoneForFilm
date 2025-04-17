package com.example.phoneforfilm.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MessageDao {

    @Query("SELECT * FROM message WHERE contactId = :contactId ORDER BY timestamp")
    fun getMessagesForContact(contactId: Int): LiveData<List<Message>>

    @Insert
    suspend fun insert(message: Message)

    @Update
    suspend fun update(message: Message)

    @Delete
    suspend fun delete(message: Message)
}
