package com.example.phoneforfilm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.phoneforfilm.data.local.entity.Conversation
import com.example.phoneforfilm.data.repository.ConversationRepository
import com.example.phoneforfilm.di.IoDispatcher
import com.example.phoneforfilm.di.MainDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ChatListViewModel @Inject constructor(
    private val repository: ConversationRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher
) : ViewModel() {

    /** LiveData van alle gesprekken (Flow → LiveData via mainDispatcher) */
    val conversations: LiveData<List<Conversation>> =
        repository.getAll().asLiveData(mainDispatcher)

    /**
     * Maak nieuw gesprek aan en geef de gegenereerde ID terug als Int.
     */
    fun createChatFor(contactId: Int, onComplete: (Int) -> Unit) {
        viewModelScope.launch(ioDispatcher) {
            val chatIdLong = repository.create(
                Conversation(contactId = contactId, lastMessage = "", timestamp = System.currentTimeMillis())
            )
            withContext(mainDispatcher) {
                onComplete(chatIdLong.toInt())
            }
        }
    }
}
