package com.selva.anime.di

import com.selva.anime.domain.usecase.AnimeUseCase
import com.selva.anime.domain.usecase.AnimeUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Binds
    @Singleton
    abstract fun bindUseCase(useCaseImpl: AnimeUseCaseImpl): AnimeUseCase
}