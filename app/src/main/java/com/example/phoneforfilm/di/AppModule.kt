package com.example.phoneforfilm.di

import android.app.Application
import androidx.room.Room
import com.example.phoneforfilm.data.AppDatabase
import com.example.phoneforfilm.data.local.dao.ContactDao
import com.example.phoneforfilm.data.local.dao.ConversationDao
import com.example.phoneforfilm.data.local.dao.MessageDao
import com.example.phoneforfilm.data.repository.ContactRepository
import com.example.phoneforfilm.data.repository.ChatRepository
import com.example.phoneforfilm.data.repository.MessageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase =
        Room.databaseBuilder(app, AppDatabase::class.java, "phoneforfilm.db")
            .build()

    @Provides
    fun provideContactDao(db: AppDatabase): ContactDao = db.contactDao()

    @Provides
    fun provideConversationDao(db: AppDatabase): ConversationDao = db.conversationDao()

    @Provides
    fun provideMessageDao(db: AppDatabase): MessageDao = db.messageDao()

    @Provides
    @Singleton
    fun provideContactRepo(dao: ContactDao): ContactRepository = ContactRepository(dao)

    @Provides
    @Singleton
    fun provideChatRepo(dao: ConversationDao): ChatRepository = ChatRepository(dao)

    @Provides
    @Singleton
    fun provideMessageRepo(dao: MessageDao): MessageRepository = MessageRepository(dao)
}
