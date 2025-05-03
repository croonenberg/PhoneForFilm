package com.example.phoneforfilm.data.repository

import com.example.phoneforfilm.data.ConversationDao
import com.example.phoneforfilm.data.Conversation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ConversationRepository(private val dao: ConversationDao) {

    suspend fun createForContact(contactId: Int): Long {
        val exists = dao.getIdByContact(contactId)
        return if (exists == null) {
            val convo = Conversation(
                contactId = contactId,
                lastMessage = "",
                timestamp = System.currentTimeMillis()
            )
            dao.insert(convo)
        } else {
            exists.toLong()
        }
    }
}
