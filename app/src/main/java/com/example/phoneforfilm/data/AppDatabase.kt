package com.example.phoneforfilm.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.phoneforfilm.data.model.Message

@Database(
    entities = [Contact::class, Message::class, Conversation::class],
    version = 7,  // bump for theme column
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao
    abstract fun contactDao(): ContactDao
    abstract fun conversationDao(): ConversationDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "phone_for_film_database"
                ).fallbackToDestructiveMigration(true).build()
                 .also { INSTANCE = it }
            }
    }
}
