package com.example.phoneforfilm.di

import android.content.Context
import androidx.room.Room
import com.example.phoneforfilm.data.AppDatabase
import com.example.phoneforfilm.data.local.dao.*
import com.example.phoneforfilm.data.local.db.MIGRATION_3_4_CHAT_THEME
import com.example.phoneforfilm.data.local.repository.ChatThemeRepository
import com.example.phoneforfilm.data.repository.ConversationRepository
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

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "phoneforfilm.db")
            .addMigrations(MIGRATION_3_4_CHAT_THEME)
            .build()

    @Provides fun provideContactDao(db: AppDatabase): ContactDao = db.contactDao()
    @Provides fun provideConversationDao(db: AppDatabase): ConversationDao = db.conversationDao()
    @Provides fun provideMessageDao(db: AppDatabase): MessageDao = db.messageDao()
    @Provides fun provideChatThemeDao(db: AppDatabase): ChatThemeDao = db.chatThemeDao()

    @Provides
    @Singleton
    fun provideChatThemeRepo(dao: ChatThemeDao): ChatThemeRepository =
        ChatThemeRepository(dao)

    @Provides
    @Singleton
    fun provideConversationRepo(dao: ConversationDao): ConversationRepository =
        ConversationRepository(dao)

    @Provides
    @Singleton
    fun providePreferencesHelper(@ApplicationContext context: Context): PreferencesHelper =
        PreferencesHelper(context)
}
