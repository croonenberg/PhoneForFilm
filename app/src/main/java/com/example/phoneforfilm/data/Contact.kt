package com.example.phoneforfilm.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val phone: String,
    val themeId: Int = 0 // 0=Greenroom, 1=Blue Stage, 2=Grey Card, 3=Neutral Light, 4=Darkroom
)
