package com.example.phoneforfilm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.phoneforfilm.data.Contact
import com.example.phoneforfilm.data.repository.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    repo: ContactRepository
) : ViewModel() {
    val allContacts: LiveData<List<Contact>> = repo.getAll()
}
