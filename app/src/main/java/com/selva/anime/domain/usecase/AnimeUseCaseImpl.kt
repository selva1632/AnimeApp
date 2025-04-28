package com.selva.anime.domain.usecase

import com.selva.anime.domain.utils.network.wrapper.Result
import com.selva.anime.domain.model.AnimeItem
import com.selva.anime.domain.repository.AnimeRepository
import javax.inject.Inject

class AnimeUseCaseImpl @Inject constructor(private val repository: AnimeRepository) : AnimeUseCase {

    override suspend fun getTopAnime(): Result<List<AnimeItem>> {
        return repository.getTopAnime()
    }

    override suspend fun getRecommendAnime(): Result<List<AnimeItem>> {
        return repository.getRecommendAnime()
    }

    override suspend fun getAnimeById(id: Int): Result<AnimeItem> {
        return repository.getAnimeById(id)
    }

    override suspend fun getAnimeByYearAndSeason(
        year: Int,
        season: String
    ): Result<List<AnimeItem>> {
        return repository.getAnimeByYearAndSeason(year, season)
    }
}