package com.example.phoneforfilm.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "conversations")
data class Conversation(
    @PrimaryKey val id: Long,
    val contactId: Int,
    val lastMessage: String,
    val timestamp: Long,
    val contactName: String,
    val theme: String?
)
