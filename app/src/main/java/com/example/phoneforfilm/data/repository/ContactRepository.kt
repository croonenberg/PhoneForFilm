package com.example.phoneforfilm.data.repository

import com.example.phoneforfilm.data.local.dao.ContactDao
import com.example.phoneforfilm.data.model.Contact
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContactRepository @Inject constructor(
    private val contactDao: ContactDao
) {

    fun getAllContacts(): Flow<List<Contact>> = contactDao.getAllContacts()

    suspend fun insert(contact: Contact) = contactDao.insert(contact)

    suspend fun update(contact: Contact) = contactDao.update(contact)

    suspend fun delete(contact: Contact) = contactDao.delete(contact)
}
