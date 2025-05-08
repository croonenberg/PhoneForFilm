package com.example.phoneforfilm.data.local.dao

import androidx.room.*
import com.example.phoneforfilm.data.local.entity.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    /**
     * Roep alle contacten op, gesorteerd op naam.
     */
    @Query("SELECT * FROM contacts ORDER BY name")
    fun getAll(): Flow<List<Contact>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(contact: Contact): Long

    @Update
    suspend fun update(contact: Contact)

    @Delete
    suspend fun delete(contact: Contact)
}
