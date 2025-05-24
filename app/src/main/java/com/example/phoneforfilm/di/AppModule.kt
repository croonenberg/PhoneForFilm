
package com.example.phoneforfilm.di

import android.content.Context
import androidx.room.Room
import com.example.phoneforfilm.data.AppDatabase
import com.example.phoneforfilm.data.local.dao.*
import com.example.phoneforfilm.data.local.repository.ChatThemeRepository
import com.example.phoneforfilm.data.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val DB_NAME = "phone_for_film.db"

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Provides fun provideContactDao(db: AppDatabase): ContactDao = db.contactDao()
    @Provides fun provideConversationDao(db: AppDatabase): ConversationDao = db.conversationDao()
    @Provides fun provideMessageDao(db: AppDatabase): MessageDao = db.messageDao()
    @Provides fun provideChatThemeDao(db: AppDatabase): ChatThemeDao = db.chatThemeDao()

    // Repositories
    @Provides
    @Singleton
    fun provideContactRepository(dao: ContactDao): ContactRepository =
        ContactRepository(dao)

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
}
