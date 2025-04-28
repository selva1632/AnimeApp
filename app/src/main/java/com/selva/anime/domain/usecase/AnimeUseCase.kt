package com.selva.anime.domain.usecase

import com.selva.anime.domain.utils.network.wrapper.Result
import com.selva.anime.domain.model.AnimeItem

interface AnimeUseCase {
    suspend fun getTopAnime(): Result<List<AnimeItem>>
    suspend fun getRecommendAnime(): Result<List<AnimeItem>>
    suspend fun getAnimeById(id: Int): Result<AnimeItem>
    suspend fun getAnimeByYearAndSeason(year: Int, season: String): Result<List<AnimeItem>>
}