package com.example.phoneforfilm.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "conversations")
/**
 * Conversation entity: koppelt een gesprek aan een contact.
 */
data class Conversation(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val contactId: Int
)
