package com.example.phoneforfilm.data.repository

import com.example.phoneforfilm.data.local.dao.ConversationDao
import com.example.phoneforfilm.data.local.entity.Conversation
import javax.inject.Inject

class ChatRepository @Inject constructor(
    private val conversationDao: ConversationDao
) {
    suspend fun createConversation(contactId: Int): Int {
        val newConversation = Conversation(contactId = contactId)
        conversationDao.insert(newConversation)
        return newConversation.id
    }
}
