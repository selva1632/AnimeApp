package com.selva.anime.di

import com.selva.anime.data.repository.AnimeRepositoryImpl
import com.selva.anime.domain.repository.AnimeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindRepository(repositoryImpl: AnimeRepositoryImpl): AnimeRepository
}