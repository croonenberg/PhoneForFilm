package com.example.phoneforfilm.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Ignore

@Entity(tableName = "conversations")
data class Conversation(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val chatId: Int = 0,
    val lastMessage: String = "",

    /** Naam die in het overzicht wordt getoond (niet opgeslagen in DB). */
    @Ignore var contactName: String = "",

    /** Epoch millis van laatste bericht voor weergave. */
    @Ignore var lastMessageTime: Long = 0L
)