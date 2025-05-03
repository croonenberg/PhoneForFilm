package com.example.phoneforfilm.di

import android.content.Context
import androidx.room.Room
import com.example.phoneforfilm.data.AppDatabase
import com.example.phoneforfilm.data.ContactDao
import com.example.phoneforfilm.data.ConversationDao
import com.example.phoneforfilm.data.dao.MessageDao
import com.example.phoneforfilm.data.repository.ContactRepository
import com.example.phoneforfilm.data.repository.ConversationRepository
import com.example.phoneforfilm.utils.PreferencesHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Provides application-wide dependencies including Room database and repositories.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Creates and provides the Room database for the application.
     */
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext ctx: Context): AppDatabase =
        Room.databaseBuilder(ctx, AppDatabase::class.java, "phone_for_film_database")
            .fallbackToDestructiveMigration(true)
            .build()

    /**
     * Provides the DAO for contacts.
     */
    @Provides
    @Singleton
    fun provideContactDao(db: AppDatabase): ContactDao =
        db.contactDao()

    /**
     * Provides the DAO for conversations.
     */
    @Provides
    @Singleton
    fun provideConversationDao(db: AppDatabase): ConversationDao =
        db.conversationDao()

    /**
     * Provides the DAO for messages.
     */
    @Provides
    @Singleton
    fun provideMessageDao(db: AppDatabase): MessageDao =
        db.messageDao()

    /**
     * Provides the repository for contacts.
     */
    @Provides
    @Singleton
    fun provideContactRepository(dao: ContactDao): ContactRepository =
        ContactRepository(dao)

    /**
     * Provides the repository for conversations.
     */
    @Provides
    @Singleton
    fun provideConversationRepository(dao: ConversationDao): ConversationRepository =
        ConversationRepository(dao)

    /**
     * Provides a helper for shared preferences.
     */
    @Provides
    @Singleton
    fun providePreferencesHelper(@ApplicationContext ctx: Context): PreferencesHelper =
        PreferencesHelper(ctx)
}
