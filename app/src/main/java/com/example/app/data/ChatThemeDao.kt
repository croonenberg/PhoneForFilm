package com.example.app.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ChatThemeDao {
    @Query("SELECT themeKey FROM chat_themes WHERE conversationId = :id")
    suspend fun getTheme(id: Long): String?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTheme(chatTheme: ChatTheme)
}
