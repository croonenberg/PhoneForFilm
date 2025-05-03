package com.example.phoneforfilm.data.repository

import androidx.lifecycle.LiveData
import com.example.phoneforfilm.data.Conversation
import com.example.phoneforfilm.data.ConversationDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConversationRepository @Inject constructor(private val dao: ConversationDao) {

    /** Stream of conversations in DB. */
    fun getAll(): LiveData<List<Conversation>> = dao.getAll()

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
