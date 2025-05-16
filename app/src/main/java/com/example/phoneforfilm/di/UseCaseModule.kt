package com.example.phoneforfilm.di

import com.example.phoneforfilm.domain.usecase.GetConversationThemeUseCase
import com.example.phoneforfilm.domain.usecase.SetConversationThemeUseCase
import com.example.phoneforfilm.data.local.repository.ChatThemeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideGetConversationThemeUseCase(repo: ChatThemeRepository): GetConversationThemeUseCase {
        return GetConversationThemeUseCase(repo)
    }

    @Provides
    @ViewModelScoped
    fun provideSetConversationThemeUseCase(repo: ChatThemeRepository): SetConversationThemeUseCase {
        return SetConversationThemeUseCase(repo)
    }
}
