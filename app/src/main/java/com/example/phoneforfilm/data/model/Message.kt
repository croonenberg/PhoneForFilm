package com.example.phoneforfilm.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
data class Message(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "conversationId")
    val conversationId: Int,

    @ColumnInfo(name = "senderId")
    val senderId: Int,

    val text: String,
    val timestamp: Long
)
