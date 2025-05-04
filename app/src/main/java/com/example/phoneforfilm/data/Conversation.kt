package com.example.phoneforfilm.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Ignore

@Entity(tableName = "conversations")
data class Conversation(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "contactId") val contactId: Int,
    @ColumnInfo(name = "lastMessage") val lastMessage: String,
    @ColumnInfo(name = "timestamp") val timestamp: Long,
    @Ignore val contactName: String = ""  // populated via JOIN in DAO
)
