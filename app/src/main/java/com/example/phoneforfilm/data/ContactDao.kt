package com.example.phoneforfilm.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(contact: Contact)

    @Query("SELECT * FROM contacts")
    fun getAll(): LiveData<List<Contact>>

    //  nieuw – aansluitend op CallViewModel
    @Query("SELECT * FROM contacts WHERE id = :id")
    fun getContactById(id: Int): LiveData<Contact>

    //  nieuw – voor updaten van contactgegevens
    @Update
    suspend fun update(contact: Contact)
}
