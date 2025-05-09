package com.example.app.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.app.data.ChatTheme
import com.example.app.data.ChatThemeDao

@Database(entities = [ChatTheme::class /*, other entities */], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun chatThemeDao(): ChatThemeDao()
    // other DAOs...
}
