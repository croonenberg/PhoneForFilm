package com.example.phoneforfilm.data.local.repository

import com.example.phoneforfilm.data.local.dao.ChatThemeDao
import com.example.phoneforfilm.data.local.entity.ChatTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ChatThemeRepository(private val dao: ChatThemeDao) {

    fun getTheme(conversationId: Long): Flow<String?> = flow {
        emit(dao.getTheme(conversationId))
    }

    suspend fun setTheme(conversationId: Long, themeKey: String) {
        dao.saveTheme(ChatTheme(conversationId, themeKey))
    }
}
