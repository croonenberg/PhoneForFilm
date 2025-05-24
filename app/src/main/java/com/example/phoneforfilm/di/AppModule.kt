package com.example.phoneforfilm.di

import android.content.Context
import androidx.room.Room
import com.example.phoneforfilm.data.AppDatabase
import com.example.phoneforfilm.data.local.dao.ChatThemeDao
import com.example.phoneforfilm.data.local.dao.MessageDao
import com.example.phoneforfilm.data.local.dao.ConversationDao
import com.example.phoneforfilm.data.local.dao.ContactDao
import com.example.phoneforfilm.utils.PreferencesHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // --- Room database ---
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "phoneforfilm.db"
        ).fallbackToDestructiveMigration(true).build()

    // --- DAO bindings ---
    @Provides @Singleton
    fun provideChatThemeDao(db: AppDatabase): ChatThemeDao = db.chatThemeDao()

    @Provides @Singleton
    fun provideMessageDao(db: AppDatabase): MessageDao = db.messageDao()

    @Provides @Singleton
    fun provideConversationDao(db: AppDatabase): ConversationDao = db.conversationDao()

    @Provides @Singleton
    fun provideContactDao(db: AppDatabase): ContactDao = db.contactDao()

    // --- Utils ---
    @Provides @Singleton
    fun providePreferencesHelper(@ApplicationContext context: Context): PreferencesHelper =
        PreferencesHelper(context)
}
