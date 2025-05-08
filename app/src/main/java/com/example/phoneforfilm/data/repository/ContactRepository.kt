package com.example.phoneforfilm.data.repository

import com.example.phoneforfilm.data.local.dao.ContactDao
import com.example.phoneforfilm.data.local.entity.Contact
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContactRepository @Inject constructor(
    private val contactDao: ContactDao
) {
    suspend fun addContact(contact: Contact) {
        contactDao.insert(contact)
    }

    suspend fun removeContact(contact: Contact) {
        contactDao.delete(contact)
    }

    fun getAllContacts(): Flow<List<Contact>> {
        return contactDao.getAllContacts()
    }
}
