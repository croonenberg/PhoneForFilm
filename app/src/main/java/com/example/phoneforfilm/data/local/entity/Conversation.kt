package com.example.phoneforfilm.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "conversations")
data class Conversation(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val contactId: Int,
    val lastMessage: String = "",
    val timestamp: Long = System.currentTimeMillis(),
    val contactName: String = "",
    val theme: String? = null
)
