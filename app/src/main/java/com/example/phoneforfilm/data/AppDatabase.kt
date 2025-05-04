package com.example.phoneforfilm.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.phoneforfilm.data.local.dao.ChatDao
import com.example.phoneforfilm.data.local.dao.MessageDao
import com.example.phoneforfilm.data.model.Chat
import com.example.phoneforfilm.data.model.Message

@Database(entities = [Message::class, Chat::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao
    abstract fun chatDao(): ChatDao
}