package com.example.phoneforfilm.domain.usecase

import com.example.phoneforfilm.data.local.repository.ChatThemeRepository
import javax.inject.Inject

class SetConversationThemeUseCase @Inject constructor(
    private val repo: ChatThemeRepository
) {
    suspend operator fun invoke(conversationId: Long, themeKey: String) {
        repo.setTheme(conversationId, themeKey)
    }
}