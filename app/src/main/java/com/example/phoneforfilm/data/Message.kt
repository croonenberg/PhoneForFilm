package com.example.phoneforfilm.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
data class Message(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val chatId: Int,
    val senderId: Long,
    val text: String,
    val timestamp: Long,
    val status: Int = 0,
    val pinned: Boolean = false,
    val favorite: Boolean = false
)
