package com.example.phoneforfilm.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "conversations")
data class Conversation(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "contactId") val contactId: Int,
    @ColumnInfo(name = "theme") val theme: String = "Greenroom" // default theme
)
