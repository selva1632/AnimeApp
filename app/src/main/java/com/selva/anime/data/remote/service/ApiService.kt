package com.selva.anime.data.remote.service

import com.selva.anime.data.remote.response.AnimeResponse
import com.selva.anime.data.remote.response.PresetResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("top/anime")
    suspend fun getTopAnime(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 20
    ): Response<AnimeResponse>

    @GET("recommendations/anime")
    suspend fun getRecommendedAnime(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 20
    ): Response<PresetResponse>

    @GET("/anime}")
    suspend fun getAnimeById(@Query("anime_id") id: Int): Response<AnimeResponse>

    @GET("seasons/{year}/{season}")
    suspend fun getSeasonAnime(
        @Path("year") year: Int,
        @Path("season") season: String,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 20
    ): Response<AnimeResponse>

    companion object {
        const val BASE_URL = "https://api.jikan.moe/v4/"
    }
}