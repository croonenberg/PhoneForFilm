package com.example.phoneforfilm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.phoneforfilm.data.AppDatabase
import com.example.phoneforfilm.data.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CallViewModel(application: Application) : AndroidViewModel(application) {

    private val contactDao = AppDatabase.getDatabase(application).contactDao()

    fun getContactById(contactId: Int): LiveData<Contact> {
        return contactDao.getContactById(contactId)
    }

    fun updateContact(contact: Contact) {
        viewModelScope.launch(Dispatchers.IO) {
            contactDao.update(contact)
        }
    }
}
