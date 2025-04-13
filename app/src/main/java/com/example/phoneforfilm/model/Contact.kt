package com.example.phoneforfilm.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val phone: String,
    val themeId: Int = 0 // Nieuw: 0 = Greenroom, 1 = Blue Stage, etc.
)
