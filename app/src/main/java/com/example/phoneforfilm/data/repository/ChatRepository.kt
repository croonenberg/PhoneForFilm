package com.example.phoneforfilm.data.repository

import com.example.phoneforfilm.data.Conversation
import com.example.phoneforfilm.data.ConversationDao
import javax.inject.Inject

class ChatRepository @Inject constructor(
    private val conversationDao: ConversationDao
) {
    suspend fun createConversation(contactId: Int): Int {
        val timestamp = System.currentTimeMillis() // Voeg de timestamp toe
        val newConversation = Conversation(contactId = contactId, timestamp = timestamp)
        conversationDao.insert(newConversation)
        return newConversation.id
    }
}
