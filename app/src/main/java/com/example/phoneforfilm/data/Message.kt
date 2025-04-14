
package com.example.phoneforfilm.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
data class Message(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val contactId: Int,
    val text: String,
    val timestamp: Long,
    val isSent: Boolean,
    val status: Int
)
