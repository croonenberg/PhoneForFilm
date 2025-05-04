package com.example.phoneforfilm.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

/**
 * Entity representing a single message within a conversation.
 */
@Entity(tableName = "messages")
data class Message(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "conversationId") val conversationId: Int,
    @ColumnInfo(name = "text") val text: String,
    @ColumnInfo(name = "timestamp") val timestamp: Long,
    @ColumnInfo(name = "isSender") val isSender: Boolean = false
)
