package com.example.phoneforfilm.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ContactDao {
    @Insert
    fun insert(contact: Contact)

    @Query("SELECT * FROM Contact")
    fun getAll(): List<Contact>
}
