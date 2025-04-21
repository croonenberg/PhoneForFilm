package com.example.phoneforfilm.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [Contact::class, Message::class, Conversation::class],
    version = 2,
    exportSchema = false
)
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
                    "phone_for_film_database"
                )
                    .fallbackToDestructiveMigration(true)
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            // Eerste dummy data
                            db.execSQL("INSERT INTO conversations(contactId, lastMessage, timestamp) VALUES(1, 'Dummy eerste bericht', strftime('%s','now')*1000)")
                            db.execSQL("INSERT INTO messages(chatId, senderId, text, timestamp, status, pinned, favorite, isDeleted) VALUES(1, 1, 'Hallo, dit is een dummybericht!', strftime('%s','now')*1000, 0, 0, 0, 0)")
                        }
                    })
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
