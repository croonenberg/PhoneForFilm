package com.example.phoneforfilm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.phoneforfilm.data.repository.ConversationRepository
import com.example.phoneforfilm.data.Conversation

@HiltViewModel
class ChatListViewModel @Inject constructor(
    private val conversationRepository: ConversationRepository
) : ViewModel() {

    val conversations: LiveData<List<Conversation>> =
        conversationRepository.getAll().asLiveData()

    fun createFor(contactId: Int) {
        viewModelScope.launch {
            // assume default lastMessage "", timestamp now
            val now = System.currentTimeMillis()
            conversationRepository.create(
                Conversation(contactId = contactId, lastMessage = "", timestamp = now, contactName = "", theme = "Greenroom")
            )
        }
    

    /** Create new conversation for contact and invoke callback with new conversationId. */
    fun createChatFor(contactId: Int, onCreated: (Int) -> Unit) {
        viewModelScope.launch {
            val newId = conversationRepository.insert(
                Conversation(contactId = contactId, lastMessage = "", timestamp = System.currentTimeMillis(), contactName = "", theme = "Greenroom")
            )
            onCreated(newId.toInt())
        }
    }
}
