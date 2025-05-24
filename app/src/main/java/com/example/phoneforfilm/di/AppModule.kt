package com.example.phoneforfilm.di

import android.content.Context
import androidx.room.Room
import com.example.phoneforfilm.db.PhoneForFilmDatabase
import com.example.phoneforfilm.data.local.dao.ChatThemeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): PhoneForFilmDatabase =
        Room.databaseBuilder(
            context,
            PhoneForFilmDatabase::class.java,
            "phoneforfilm.db"
        ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideChatThemeDao(db: PhoneForFilmDatabase): ChatThemeDao = db.chatThemeDao()
}
