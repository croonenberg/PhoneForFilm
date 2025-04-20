package com.example.phoneforfilm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.phoneforfilm.data.AppDatabase
import com.example.phoneforfilm.data.Contact
import com.example.phoneforfilm.data.ContactRepository
import kotlinx.coroutines.launch

class ContactViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ContactRepository
    val allContacts: LiveData<List<Contact>>

    init {
        val contactDao = AppDatabase.getDatabase(application).contactDao()
        repository = ContactRepository(contactDao)
        allContacts = repository.allContacts
    }

    fun insert(contact: Contact) = viewModelScope.launch {
        repository.insert(contact)
    }

    fun update(contact: Contact) = viewModelScope.launch {
        repository.update(contact)
    }

    fun delete(contact: Contact) = viewModelScope.launch {
        repository.delete(contact)
    }

    fun getContactById(id: Int) = repository.getContactById(id)
}
