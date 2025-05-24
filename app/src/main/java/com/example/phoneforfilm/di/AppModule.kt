package com.example.phoneforfilm.di

import android.content.Context
import androidx.room.Room
import com.example.phoneforfilm.data.AppDatabase
import com.example.phoneforfilm.data.local.dao.ChatThemeDao
import com.example.phoneforfilm.data.local.dao.ContactDao
import com.example.phoneforfilm.data.local.dao.ConversationDao
import com.example.phoneforfilm.data.repository.ConversationRepository
import com.example.phoneforfilm.data.repository.MessageRepository
import com.example.phoneforfilm.data.local.dao.MessageDao
import com.example.phoneforfilm.data.local.repository.ChatThemeRepository
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

    private const val DB_NAME = "phone_for_film.db"

    // --- Database & DAO's -------------------------------------------------

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
            // MIGRATIONS: voeg hier toekomstige migraties toe
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideConversationDao(db: AppDatabase): ConversationDao = db.conversationDao()

    @Provides
    fun provideMessageDao(db: AppDatabase): MessageDao = db.messageDao()

    @Provides
    fun provideChatThemeDao(db: AppDatabase): ChatThemeDao = db.chatThemeDao()

    // --- Repositories -----------------------------------------------------

    @Provides
    @Singleton
    fun provideConversationRepository(dao: ConversationDao): ConversationRepository =
        ConversationRepository(dao)

    @Provides
    @Singleton
    fun provideMessageRepository(dao: MessageDao): MessageRepository =
        MessageRepository(dao)

    @Provides
    @Singleton
    fun provideChatThemeRepository(dao: ChatThemeDao): ChatThemeRepository =
        ChatThemeRepository(dao)

    // --- Preferences ------------------------------------------------------

    @Provides
    @Singleton
    fun providePreferencesHelper(@ApplicationContext context: Context): PreferencesHelper =
        PreferencesHelper(context)
}