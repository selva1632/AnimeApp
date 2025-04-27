package com.selva.anime.domain.repository

import com.selva.anime.utils.network.wrapper.Result
import com.selva.anime.domain.model.AnimeItem

interface AnimeRepository {
    suspend fun getTopAnime(): Result<List<AnimeItem>>
    suspend fun getRecommendAnime(): Result<List<AnimeItem>>
    suspend fun getAnimeById(id: Int): Result<AnimeItem>
    suspend fun getAnimeByYearAndSeason(year: Int, season: String): Result<List<AnimeItem>>
}