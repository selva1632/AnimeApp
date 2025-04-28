package com.selva.anime.di

import com.selva.anime.domain.usecase.AnimeUseCase
import com.selva.anime.presentation.model.AnimeModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AnimeModule {
    @Provides
    fun provideAnimeModel(useCase: AnimeUseCase): AnimeModel {
        return AnimeModel(useCase)
    }
}