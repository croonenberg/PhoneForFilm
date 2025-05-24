package com.example.phoneforfilm.domain.usecase

import com.example.phoneforfilm.data.local.entity.Conversation
import com.example.phoneforfilm.data.repository.ConversationRepository
import javax.inject.Inject

/**
 * Maakt (of haalt) een gesprek voor het opgegeven contact en retourneert het ID.
 */
class CreateConversationUseCase @Inject constructor(
    private val repo: ConversationRepository
) {
    suspend operator fun invoke(contactId: Int, contactName: String = ""): Long {
        // Controleer of er al een gesprek bestaat
        val existing = repo.findByContact(contactId)
        if (existing != null) return existing.toLong()

        val conversation = Conversation(
            contactId = contactId,
            contactName = contactName,
            lastMessage = "",
            timestamp = System.currentTimeMillis()
        )
        return repo.create(conversation)
    }
}