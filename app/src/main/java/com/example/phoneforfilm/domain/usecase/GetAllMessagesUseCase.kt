package com.example.phoneforfilm.domain.usecase

import com.example.phoneforfilm.data.repository.MessageRepository
import kotlinx.coroutines.flow.Flow
import com.example.phoneforfilm.data.local.entity.Message
import javax.inject.Inject

/**
 * Geeft alle berichten voor een conversatie als Flow terug.
 */
class GetAllMessagesUseCase @Inject constructor(
    private val repo: MessageRepository
) {
    operator fun invoke(conversationId: Int): Flow<List<Message>> =
        repo.getMessagesFor(conversationId)
}