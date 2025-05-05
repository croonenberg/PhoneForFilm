package com.example.phoneforfilm.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phoneforfilm.data.model.Contact
import com.example.phoneforfilm.data.repository.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    private val repository: ContactRepository
) : ViewModel() {

    val contacts = repository.getAllContacts()
        .map { it.sortedBy { c -> c.name.lowercase() } }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun insert(contact: Contact) {
        viewModelScope.launch {
            repository.insert(contact)
        }
    }

    fun update(contact: Contact) {
        viewModelScope.launch {
            repository.update(contact)
        }
    }

    fun delete(contact: Contact) {
        viewModelScope.launch {
            repository.delete(contact)
        }
    }
}
