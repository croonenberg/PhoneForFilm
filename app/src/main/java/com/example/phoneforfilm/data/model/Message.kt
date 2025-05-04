package com.example.phoneforfilm.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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
) {
    /**
     * Formatted time string for display (HH:mm).
     */
    val formattedTime: String
        get() = SimpleDateFormat("HH:mm", Locale.getDefault())
            .format(Date(timestamp))
}
