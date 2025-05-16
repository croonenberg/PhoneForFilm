package com.example.phoneforfilm.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "conversations")
data class Conversation(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,

    @ColumnInfo(name = "contactId")
    val contactId: Int,

    @ColumnInfo(name = "lastMessage")
    val lastMessage: String = "",

    @ColumnInfo(name = "timestamp")
    val timestamp: Long,

    @ColumnInfo(name = "contactName")
    val contactName: String = "",

    @ColumnInfo(name = "theme")
    val theme: String = "Greenroom"
)
