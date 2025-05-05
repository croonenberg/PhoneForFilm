package com.example.phoneforfilm.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val phoneNumber: String
    val androidContactId: Long? = null // <-- voeg deze toe
)
@ColumnInfo(name = "androidContactId")
val androidContactId: Long?
