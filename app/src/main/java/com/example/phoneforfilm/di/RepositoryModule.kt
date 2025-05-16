package com.example.phoneforfilm.di

import com.example.phoneforfilm.data.local.repository.ChatThemeRepository
import com.example.phoneforfilm.data.local.dao.ChatThemeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideChatThemeRepository(dao: ChatThemeDao): ChatThemeRepository {
        return ChatThemeRepository(dao)
    }
}
