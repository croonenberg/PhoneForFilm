package com.example.phoneforfilm.di

import android.content.Context
import androidx.room.Room
import com.example.phoneforfilm.data.*
import com.example.phoneforfilm.data.repository.ContactRepository
import com.example.phoneforfilm.data.repository.ConversationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /* ---------- Room database ---------- */
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext ctx: Context): AppDatabase =
        Room.databaseBuilder(ctx, AppDatabase::class.java, "phone_for_film_database")
            .fallbackToDestructiveMigration()
            .build()

    /* ---------- DAOs ---------- */

    @Provides
    @Singleton
    fun provideContactDao(db: AppDatabase): ContactDao = db.contactDao()

    @Provides
    @Singleton
    fun provideConversationDao(db: AppDatabase): ConversationDao = db.conversationDao()

    @Provides
    @Singleton
    fun provideMessageDao(db: AppDatabase): MessageDao = db.messageDao()

    /* ---------- Repositories ---------- */

    @Provides
    @Singleton
    fun provideContactRepository(dao: ContactDao): ContactRepository = ContactRepository(dao)

    @Provides
    @Singleton
    fun provideConversationRepository(dao: ConversationDao): ConversationRepository =
        ConversationRepository(dao)

    /* MessageRepository has @Inject constructor, so Hilt can provide it automatically */

    /* ---------- Other singletons ---------- */
}
