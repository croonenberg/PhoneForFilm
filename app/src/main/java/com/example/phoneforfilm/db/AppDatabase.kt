package com.example.phoneforfilm.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.phoneforfilm.data.ChatTheme
import com.example.phoneforfilm.data.ChatThemeDao

@Database(entities = [ChatTheme::class /*, other entities */], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun chatThemeDao(): ChatThemeDao()
    // other DAOs...
}
