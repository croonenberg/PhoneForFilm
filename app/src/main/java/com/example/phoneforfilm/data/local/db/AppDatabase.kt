package com.example.phoneforfilm.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.phoneforfilm.data.model.Message
import com.example.phoneforfilm.data.local.dao.MessageDao

@Database(entities = [Message::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun messageDao(): MessageDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "phone_for_film_database"
                ).build().also { instance = it }
            }
    }
}