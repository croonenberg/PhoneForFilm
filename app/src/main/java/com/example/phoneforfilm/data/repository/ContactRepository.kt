package com.example.phoneforfilm.data.repository

import androidx.lifecycle.LiveData
import com.example.phoneforfilm.data.Contact
import com.example.phoneforfilm.data.ContactDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContactRepository @Inject constructor(private val dao: ContactDao) {

    private val allContactsInternal: LiveData<List<Contact>> = dao.getAll()

    /** Return a stream of all contacts ordered by name. */
    fun getAll(): LiveData<List<Contact>> = allContactsInternal

    suspend fun insert(contact: Contact) = dao.insert(contact)

    suspend fun update(contact: Contact) = dao.update(contact)

    suspend fun delete(contact: Contact) = dao.delete(contact)

    fun getContactById(id: Int): Contact? = dao.getContactById(id)
}
