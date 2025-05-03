package com.example.phoneforfilm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phoneforfilm.data.Conversation
import com.example.phoneforfilm.data.repository.ConversationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * ViewModel voor de lijst met conversaties.
 * Verzorgt ophalen van conversations en aanmaken van nieuwe chats.
 */
@HiltViewModel
class ChatListViewModel @Inject constructor(
    private val repository: ConversationRepository
) : ViewModel() {

    /** LiveData-stream van alle Conversation-objecten uit de database. */
    val conversations: LiveData<List<Conversation>> = repository.getAll()

    /**
     * Maakt (indien nodig) een Conversation aan voor [contactId] en
     * retourneert het nieuwe chatId via de [onComplete]-callback.
     */
    fun createChatFor(contactId: Int, onComplete: (Int) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val chatId = repository.createForContact(contactId).toInt()
            withContext(Dispatchers.Main) {
                onComplete(chatId)
            }
        }
    }
}

