package com.example.phoneforfilm.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message")
data class Message(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val contactId: Int,
    val timestamp: Long,
    val text: String,
    val isSent: Boolean,
    val status: String,
    val theme: String = "greenroom"
)
