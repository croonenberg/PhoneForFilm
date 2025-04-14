package com.example.phoneforfilm.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Message(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val contactId: Int,
    val content: String,
    val timestamp: Long,
    val isSent: Boolean
)
