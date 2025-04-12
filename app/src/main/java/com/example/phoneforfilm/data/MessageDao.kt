package com.example.phoneforfilm.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MessageDao {
    @Insert
    fun insert(message: Message)

    @Query("SELECT * FROM Message")
    fun getAll(): List<Message>
}
