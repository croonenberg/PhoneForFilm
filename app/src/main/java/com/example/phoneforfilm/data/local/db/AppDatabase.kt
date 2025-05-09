package com.example.phoneforfilm.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.phoneforfilm.data.local.dao.ChatThemeDao
import com.example.phoneforfilm.data.local.entity.ChatTheme
// ... other imports

@Database(
    entities = [
        ChatTheme::class,
        // ... other entities
    ],
    version = 4, // bumped from 3 to 4
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun chatThemeDao(): ChatThemeDao()
    // ... other DAOs
}
