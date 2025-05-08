package com.example.phoneforfilm.data.repository

import com.example.phoneforfilm.data.local.dao.ConversationDao
import com.example.phoneforfilm.data.local.entity.Conversation
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRepository @Inject constructor(
    private val convoDao: ConversationDao
) {
    /**
     * Creates a conversation for the given contact ID and returns its database ID.
     */
    suspend fun createConversation(contactId: Int): Int {
        convoDao.insert(Conversation(contactId = contactId))
        return convoDao.findByContact(contactId)
            ?: throw IllegalStateException("Conversation not found after insert")
    }
}
