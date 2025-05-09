package com.example.phoneforfilm.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "messages",
    indices = [Index("conversationId")]
)
data class Message(
    @PrimaryKey val id: Long,
    val conversationId: Long,
    val senderId: Int,
    val body: String,
    val timestamp: Long,
    val status: Int
)
