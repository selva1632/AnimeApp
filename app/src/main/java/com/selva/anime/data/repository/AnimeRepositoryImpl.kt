package com.selva.anime.data.repository

import com.selva.anime.data.local.dao.AnimeDao
import com.selva.anime.data.local.mapper.toAnimeItem
import com.selva.anime.data.remote.mapper.toAnimeEntity
import com.selva.anime.data.remote.mapper.toAnimeItem
import com.selva.anime.data.remote.service.ApiService
import com.selva.anime.domain.model.AnimeItem
import com.selva.anime.domain.repository.AnimeRepository
import com.selva.anime.utils.network.wrapper.Result
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnimeRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val animeDao: AnimeDao,
) : AnimeRepository {
    override suspend fun getTopAnime(): Result<List<AnimeItem>> {
        val localTopAnimeList = animeDao.getTopAnime()
        if (localTopAnimeList.isNotEmpty()) {
            return Result.Success(localTopAnimeList.map { it.toAnimeItem() })
        }

        delay(300L)
        val response = apiService.getTopAnime()
        return if (response.isSuccessful) {
            response.body()?.let { animeData ->
                val animeEntities = animeData.data.map {
                    it.toAnimeEntity().copy(isTop = true)
                }
                animeDao.insertAnimeList(animeEntities)
                Result.Success(animeData.data.map { it.toAnimeItem() })
            } ?: Result.Error("Null body")
        } else {
            Result.Error(response.message())
        }
    }

    override suspend fun getRecommendAnime(): Result<List<AnimeItem>> {
        val localRecommendAnime = animeDao.getRecommendedAnime()
        if (localRecommendAnime.isNotEmpty()) {
            return Result.Success(localRecommendAnime.map { it.toAnimeItem() })
        }

        delay(300L)
        val response = apiService.getRecommendedAnime()
        return if (response.isSuccessful) {
            response.body()?.let { body ->
                val animeItems = body.data
                    .flatMap { it.entry }
                    .take(20)

                animeDao.insertAnimeList(animeItems.map {
                    it.toAnimeEntity().copy(isRecommended = true)
                })
                Result.Success(animeItems.map { it.toAnimeItem() })
            } ?: Result.Error("Null Body")
        } else {
            Result.Error(response.message())
        }
    }

    override suspend fun getAnimeById(id: Int): Result<AnimeItem> {
        val localAnimeById = animeDao.getAnimeById(id)
        localAnimeById?.let {
            return Result.Success(localAnimeById.toAnimeItem())
        } ?: run {
            val response = apiService.getAnimeById(id)
            return if (response.isSuccessful) {
                response.body()?.let { data ->
                    val anime = data.data.first().toAnimeItem()
                    Result.Success(anime)
                } ?: Result.Error("Null Body")
            } else {
                Result.Error(response.message())
            }
        }
    }

    override suspend fun getAnimeByYearAndSeason(
        year: Int,
        season: String
    ): Result<List<AnimeItem>> {
        val localAnimeByYear = animeDao.getAnimeByYear(year)
        if (localAnimeByYear.isNotEmpty()) {
            return Result.Success(localAnimeByYear.map { it.toAnimeItem() })
        }

        delay(500L)
        val response = apiService.getSeasonAnime(year, season)
        return if (response.isSuccessful) {
            response.body()?.let { data ->
                val anime = data.data
                animeDao.insertAnimeList(anime.map { it.toAnimeEntity() })
                Result.Success(anime.map { it.toAnimeItem() })
            } ?: Result.Error("Null Empty")
        } else {
            Result.Error(response.message())
        }
    }
}