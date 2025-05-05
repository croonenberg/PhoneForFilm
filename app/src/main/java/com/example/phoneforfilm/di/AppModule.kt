package com.example.phoneforfilm.di

import android.content.Context
import com.example.phoneforfilm.data.ConversationDao
import com.example.phoneforfilm.data.local.dao.ContactDao
import com.example.phoneforfilm.data.local.dao.MessageDao
import com.example.phoneforfilm.data.local.db.AppDatabase
import com.example.phoneforfilm.data.repository.ContactRepository
import com.example.phoneforfilm.data.repository.MessageRepository
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
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideMessageDao(database: AppDatabase): MessageDao {
        return database.messageDao()
    }

    @Provides
    @Singleton
    fun provideContactDao(database: AppDatabase): ContactDao {
        return database.contactDao()
    }

    @Provides
    @Singleton
    fun provideMessageRepository(messageDao: MessageDao): MessageRepository {
        return MessageRepository(messageDao)
    }

    @Provides
    @Singleton
    fun provideContactRepository(contactDao: ContactDao): ContactRepository {
        return ContactRepository(contactDao)
    }

    @Provides
    @Singleton
    fun provideConversationDao(database: AppDatabase): ConversationDao {
        return database.conversationDao()
    }

    @Provides
    @Singleton
    fun providePreferencesHelper(@ApplicationContext context: Context): PreferencesHelper {
        return PreferencesHelper(context)
    }
}



