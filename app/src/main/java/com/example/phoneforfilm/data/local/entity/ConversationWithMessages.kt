package com.example.phoneforfilm.data.local.entity
@file:Suppress("unused", "UnusedImport")

import androidx.room.Embedded
import androidx.room.Relation

data class ConversationWithMessages(
    @Embedded val conversation: Conversation,
    @Relation(
        parentColumn = "id",
        entityColumn = "conversationId"
    )
    val messages: List<Message>
)
