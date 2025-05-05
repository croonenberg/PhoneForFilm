package com.example.phoneforfilm.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.phoneforfilm.data.model.Contact

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
