package com.example.phoneforfilm.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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
) {
    // Voeg deze property toe om de formatted time te verkrijgen
    val formattedTime: String
        get() = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(timestamp))
}
