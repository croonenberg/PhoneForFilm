package com.example.phoneforfilm.di

import android.content.Context
import androidx.room.Room
import com.example.phoneforfilm.data.local.db.AppDatabase
import com.example.phoneforfilm.data.local.db.MIGRATION_3_4_CHAT_THEME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "phoneforfilm_db"
        )
        .addMigrations(MIGRATION_3_4_CHAT_THEME)
        .build()
    }

    @Provides
    fun provideChatThemeDao(db: AppDatabase) = db.chatThemeDao()
    // ... other providers
}
