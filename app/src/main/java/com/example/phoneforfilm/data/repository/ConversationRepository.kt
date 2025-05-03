package com.example.phoneforfilm.data.repository

import androidx.lifecycle.LiveData
import com.example.phoneforfilm.data.Conversation
import com.example.phoneforfilm.data.ConversationDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConversationRepository @Inject constructor(private val dao: ConversationDao) {

    private val allConversationsInternal: LiveData<List<Conversation>> = dao.getAll()

    /** Stream of conversations in DB. */
    fun getAll(): LiveData<List<Conversation>> = allConversationsInternal

    suspend fun insert(conversation: Conversation): Long = dao.insert(conversation)

    suspend fun update(conversation: Conversation) = dao.update(conversation)

    suspend fun delete(conversation: Conversation) = dao.delete(conversation)

suspend fun createForContact(contactId: Int) {
    val exists = dao.getIdByContact(contactId)
    if (exists == null) {
        val convo = com.example.phoneforfilm.data.Conversation(
            contactId = contactId,
            lastMessage = "",
            timestamp = System.currentTimeMillis()
        )
        dao.insert(convo)
    }
}

}