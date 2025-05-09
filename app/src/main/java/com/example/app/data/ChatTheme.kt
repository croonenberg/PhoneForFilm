package com.example.app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chat_themes")
data class ChatTheme(
    @PrimaryKey val conversationId: Long,
    val themeKey: String // e.g. "greenroom", "bluestage", etc.
)
