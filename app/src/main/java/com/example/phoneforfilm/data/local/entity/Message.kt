package com.example.phoneforfilm.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "messages",
    foreignKeys = [
        ForeignKey(
            entity = Conversation::class,
            parentColumns = ["id"],
            childColumns = ["conversationId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
/**
 * Message entity: id, gesprek, afzender, inhoud, timestamp en status.
 */
data class Message(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val conversationId: Int,
    val senderId: Int,
    val body: String,
    val timestamp: Long,
    val status: Int // 0=sent,1=delivered,2=read
)
