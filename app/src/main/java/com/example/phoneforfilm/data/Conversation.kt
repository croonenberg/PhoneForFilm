package com.example.phoneforfilm.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Ignore

@Entity(tableName = "conversations")
data class Conversation(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "contactId") val contactId: Int,
    val lastMessage: String,
    @ColumnInfo(name = "timestamp") val timestamp: Long,

    /** Niet in DB opgeslagen velden voor UI-weergave */
    @Ignore var contactName: String = ""
)