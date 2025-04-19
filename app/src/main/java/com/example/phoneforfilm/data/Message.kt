package com.example.phoneforfilm.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
data class Message(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val chatId: Int,
    val text: String,
    val isSent: Boolean,
    val timestamp: Long,
    val status: Int = 0,        // 0 = sent, 1 = delivered, 2 = read
    val pinned: Boolean = false,
    val favorite: Boolean = false
)
