package com.example.phoneforfilm.data

import androidx.lifecycle.LiveData

class ConversationRepository(private val dao: ConversationDao) {
    val allConversations: LiveData<List<Conversation>> = dao.getAllConversations()

    suspend fun insert(conversation: Conversation): Long =
        dao.insertConversation(conversation)

    suspend fun update(conversation: Conversation) =
        dao.updateConversation(conversation)

    suspend fun delete(conversation: Conversation) =
        dao.deleteConversation(conversation)
}
