package com.example.phoneforfilm.data.repository
@file:Suppress("unused", "UnusedImport")

import com.example.phoneforfilm.data.local.dao.ContactDao
import com.example.phoneforfilm.data.local.entity.Contact
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContactRepository @Inject constructor(
    private val dao: ContactDao
) {
    /**
     * Returns all contacts as a Flow, sorted by name.
     */
    fun getAllContacts(): Flow<List<Contact>> = dao.getAll()

    /**
     * Inserts or replaces a contact and returns the new row ID.
     */
    suspend fun addOrUpdate(contact: Contact): Long = dao.insert(contact)

    /**
     * Deletes the given contact.
     */
    suspend fun delete(contact: Contact) = dao.delete(contact)
}
