package com.example.phoneforfilm.data

class ContactRepository(private val dao: ContactDao) {
    val allContacts = dao.getAll()

    suspend fun insert(contact: Contact) = dao.insert(contact)

    suspend fun update(contact: Contact) = dao.update(contact)

    suspend fun delete(contact: Contact) = dao.delete(contact)

    fun getContactById(id: Int): Contact? = dao.getContactById(id)
}
