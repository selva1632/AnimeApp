package com.selva.anime.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime_table")
data class AnimeEntity(
    @PrimaryKey
    @ColumnInfo(name = "anime_id") val animeId: Int,
    @ColumnInfo(name = "anime_name") val animeName: String,
    @ColumnInfo(name = "episode") val episode: Int = 0,
    @ColumnInfo(name = "rating") val rating: String,
    @ColumnInfo(name = "image_url") val imageUrl: String? = null,
    @ColumnInfo(name = "youtube_url") val youtubeUrl: String? = null,
    @ColumnInfo(name = "youtube_id") val youtubeId: String? = null,
    @ColumnInfo(name = "year") val year: Int,
    @ColumnInfo(name = "is_top") val isTop: Boolean = false,
    @ColumnInfo(name = "is_recommended") val isRecommended: Boolean = false,
)
