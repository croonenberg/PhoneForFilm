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

@HiltViewModel
class ChatListViewModel @Inject constructor(
    private val repository: ConversationRepository
) : ViewModel() {

    /** Stream of conversations from the repository */
    val conversations: LiveData<List<Conversation>> = repository.getAll()

    fun createChatFor(contactId: Int, onComplete: (Int) -> Unit) {
        // Run repository call on IO dispatcher to avoid main thread DB access
        viewModelScope.launch(Dispatchers.IO) {
            val chatId = repository.createForContact(contactId).toInt()
            // Switch back to Main for callback
            withContext(Dispatchers.Main) {
                onComplete(chatId)
            }
        }
    }
}
