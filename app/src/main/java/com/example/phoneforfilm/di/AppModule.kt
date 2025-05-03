package com.example.phoneforfilm.di

import android.content.Context
import com.example.phoneforfilm.data.AppDatabase
import com.example.phoneforfilm.data.ContactDao
import com.example.phoneforfilm.data.ContactRepository
import com.example.phoneforfilm.data.MessageDao
import com.example.phoneforfilm.data.MessageRepository
import com.example.phoneforfilm.data.ConversationDao
import com.example.phoneforfilm.data.ConversationRepository

import com.example.phoneforfilm.utils.LocaleManager
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

    @Singleton
    @Provides
    fun providePreferencesHelper(@ApplicationContext context: Context): PreferencesHelper =
        PreferencesHelper(context)

    @Singleton
    @Provides
    fun provideLocaleManager(@ApplicationContext context: Context): LocaleManager =
        LocaleManager

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        AppDatabase.getDatabase(context)

    @Singleton
    @Provides
    fun provideMessageDao(db: AppDatabase): MessageDao =
        db.messageDao()

    @Singleton
    @Provides
    fun provideContactDao(db: AppDatabase): ContactDao =
        db.contactDao()

    @Singleton
    @Provides
    fun provideMessageRepository(dao: MessageDao): MessageRepository =
        MessageRepository(dao)

    @Provides
    fun provideConversationDao(db: AppDatabase): ConversationDao = db.conversationDao()

    @Provides
    fun provideConversationRepository(dao: ConversationDao): ConversationRepository =
        ConversationRepository(dao)


    @Singleton
    @Provides
    fun provideContactRepository(dao: ContactDao): ContactRepository =
        ContactRepository(dao)
}
