package com.example.phoneforfilm.domain.usecase

import com.example.phoneforfilm.data.local.repository.ChatThemeRepository

class SetConversationThemeUseCase(private val repo: ChatThemeRepository) {
    suspend operator fun invoke(conversationId: Long, themeKey: String) {
        repo.setTheme(conversationId, themeKey)
    }
}
