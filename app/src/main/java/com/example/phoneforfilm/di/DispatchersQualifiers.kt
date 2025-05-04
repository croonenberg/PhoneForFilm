package com.example.phoneforfilm.di

import javax.inject.Qualifier
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineDispatcher

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IoDispatcher

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainDispatcher
