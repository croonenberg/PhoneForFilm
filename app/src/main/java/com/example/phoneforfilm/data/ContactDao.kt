package com.example.phoneforfilm.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ContactDao {
    @Query("SELECT * FROM contacts ORDER BY name ASC")
    fun getAll(): LiveData<List<Contact>>

    @Query("SELECT * FROM contacts")
    fun getAllNow(): List<Contact>

    @Query("SELECT id FROM contacts WHERE androidContactId = :androidId LIMIT 1")
    fun getIdByAndroidId(androidId: Long): Int?
    @Query("SELECT * FROM contacts WHERE id = :id LIMIT 1")
    fun getContactById(id: Int): Contact?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(contact: Contact): Long

    @Update
    suspend fun update(contact: Contact)

    @Delete
    suspend fun delete(contact: Contact)
}