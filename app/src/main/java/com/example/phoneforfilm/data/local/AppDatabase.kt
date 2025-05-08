package com.example.phoneforfilm.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.phoneforfilm.data.local.dao.ContactDao
import com.example.phoneforfilm.data.local.dao.ConversationDao
import com.example.phoneforfilm.data.local.dao.MessageDao
import com.example.phoneforfilm.data.local.entity.Contact
import com.example.phoneforfilm.data.local.entity.Conversation
import com.example.phoneforfilm.data.local.entity.Message

@Database(
    entities = [Contact::class, Conversation::class, Message::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao
    abstract fun conversationDao(): ConversationDao
    abstract fun messageDao(): MessageDao
}
