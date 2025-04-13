package com.example.phoneforfilm.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Message(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val content: String,
    val timestamp: Long,
    val sentByUser: Boolean,
    val status: Int // 0 = sent, 1 = delivered, 2 = read
)
