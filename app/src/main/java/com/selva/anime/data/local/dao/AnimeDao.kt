package com.selva.anime.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.selva.anime.data.local.model.AnimeEntity

@Dao
interface AnimeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnime(anime: AnimeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnimeList(animeList: List<AnimeEntity>)

    @Query("SELECT * FROM anime_table WHERE is_top = 1")
    suspend fun getTopAnime(): List<AnimeEntity>

    @Query("SELECT * FROM anime_table WHERE is_recommended = 1")
    suspend fun getRecommendedAnime(): List<AnimeEntity>

    @Query("SELECT * FROM anime_table WHERE year = :year")
    suspend fun getAnimeByYear(year: Int): List<AnimeEntity>

    @Query("SELECT * FROM anime_table WHERE anime_id = :id")
    suspend fun getAnimeById(id: Int): AnimeEntity?
}

