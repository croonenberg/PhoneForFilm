package com.example.phoneforfilm.di

import android.content.Context
import com.example.phoneforfilm.data.ContactDao
import com.example.phoneforfilm.data.repository.ContactRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideContactRepository(dao: ContactDao): ContactRepository =
        ContactRepository(dao)
}
