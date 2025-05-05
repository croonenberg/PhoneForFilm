package com.example.phoneforfilm.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.phoneforfilm.data.ConversationDao
import com.example.phoneforfilm.data.local.dao.ContactDao
import com.example.phoneforfilm.data.local.dao.MessageDao
import com.example.phoneforfilm.data.model.Contact
import com.example.phoneforfilm.data.model.Message

@Database(entities = [Message::class, Contact::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun messageDao(): MessageDao
    abstract fun contactDao(): ContactDao
    abstract fun conversationDao(): ConversationDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "phoneforfilm_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}