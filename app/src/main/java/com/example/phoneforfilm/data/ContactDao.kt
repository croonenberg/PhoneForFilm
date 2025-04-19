package com.example.phoneforfilm.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(contact: Contact)

    @Query("SELECT * FROM contacts")
    fun getAll(): LiveData<List<Contact>>

    // suspend function to get list of contacts synchronously
    @Query("SELECT * FROM contacts")
    suspend fun getAllList(): List<Contact>

    @Query("SELECT * FROM contacts WHERE id = :id")
    fun getContactById(id: Int): LiveData<Contact>

    @Update
    suspend fun update(contact: Contact)
}
