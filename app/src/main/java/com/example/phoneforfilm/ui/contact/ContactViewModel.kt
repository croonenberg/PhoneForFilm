package com.example.phoneforfilm.ui.contact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phoneforfilm.data.local.dao.ContactDao
import com.example.phoneforfilm.data.local.entity.Contact
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    private val contactDao: ContactDao
) : ViewModel() {
    val contacts: StateFlow<List<Contact>> = contactDao.getAll()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
}
