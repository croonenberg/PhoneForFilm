package com.example.phoneforfilm.domain.usecase

import com.example.phoneforfilm.data.local.repository.ChatThemeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetConversationThemeUseCase @Inject constructor(
    private val repo: ChatThemeRepository
) {
    operator fun invoke(conversationId: Long): Flow<String?> =
        repo.getTheme(conversationId)
}