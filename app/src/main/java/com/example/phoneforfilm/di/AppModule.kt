package com.example.phoneforfilm.di

import android.content.Context
import androidx.room.Room
import com.example.phoneforfilm.data.AppDatabase
import com.example.phoneforfilm.data.ContactDao
import com.example.phoneforfilm.data.ConversationDao
import com.example.phoneforfilm.data.MessageDao
import com.example.phoneforfilm.data.repository.ContactRepository
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

    /**
     *
     *//* ---------- Room database ---------- */
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext ctx: Context): AppDatabase =
        Room.databaseBuilder(ctx, AppDatabase::class.java, "phone_for_film_database")
            .fallbackToDestructiveMigration(true)
            .build()

    /**
     *
     *//* ---------- DAOs ---------- */
    @Provides @Singleton fun provideContactDao(db: AppDatabase): ContactDao = db.contactDao()
    /**
     *
     */
    @Provides @Singleton fun provideConversationDao(db: AppDatabase): ConversationDao = db.conversationDao()
    /**
     *
     */
    @Provides @Singleton fun provideMessageDao(db: AppDatabase): MessageDao = db.messageDao()

    /**
     *
     *//* ---------- Repositories ---------- */
    @Provides @Singleton fun provideContactRepository(dao: ContactDao): ContactRepository = ContactRepository(dao)
    /**
     *
     */
    @Provides @Singleton fun provideConversationRepository(dao: ConversationDao): ConversationRepository = ConversationRepository(dao)

    /**
     *
     *//* ---------- Other singletons ---------- */
    @Provides
    @Singleton
    fun providePreferencesHelper(@ApplicationContext ctx: Context): PreferencesHelper =
        PreferencesHelper(ctx)
}
