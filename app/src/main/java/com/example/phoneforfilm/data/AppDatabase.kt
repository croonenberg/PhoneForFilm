package com.example.phoneforfilm.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.example.phoneforfilm.data.model.Message

import com.example.phoneforfilm.data.dao.MessageDao

/**
 * Room database definition for PhoneForFilm.
 */
@Database(
    entities = [Contact::class, Message::class, Conversation::class],
    version = 4,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    /** DAO for messages. */
    abstract fun messageDao(): MessageDao

    /** DAO for contacts. */
    abstract fun contactDao(): ContactDao

    /** DAO for conversations. */
    abstract fun conversationDao(): ConversationDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * Returns the singleton database instance.
         */
        fun getDatabase(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "phone_for_film_database"
                )
                    .fallbackToDestructiveMigration(true)
                    .build()
                    .also { INSTANCE = it }
            }
    }
}
