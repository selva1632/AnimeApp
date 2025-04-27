package com.selva.anime.data.remote.mapper

import com.selva.anime.data.local.model.AnimeEntity
import com.selva.anime.data.remote.model.DataDto
import com.selva.anime.data.remote.model.EntryDto
import com.selva.anime.domain.model.AnimeItem

fun DataDto.toAnimeItem(): AnimeItem {
    return AnimeItem(
        id = malId,
        title = title,
        episode = episodes,
        rating = rating,
        imageUrl = images?.jpg?.imageUrl,
        youtubeId = trailer?.youtubeId,
        youtubeUrl = url,
        isSelected = false
    )
}

fun DataDto.toAnimeEntity(): AnimeEntity {
    return AnimeEntity(
        animeId = malId,
        animeName = title,
        episode = episodes,
        rating = rating,
        imageUrl = images?.jpg?.imageUrl,
        youtubeUrl = url,
        youtubeId = trailer?.youtubeId,
        year = year ?: 0,
        isTop = false,
        isRecommended = false
    )
}

fun EntryDto.toAnimeItem(): AnimeItem {
    return AnimeItem(
        id = malId,
        title = title,
        episode = 0,
        rating = "0",
        imageUrl = images.jpg?.imageUrl,
        youtubeId = "",
        youtubeUrl = url,
        isSelected = false
    )
}

fun EntryDto.toAnimeEntity(): AnimeEntity {
    return AnimeEntity(
        animeId = malId,
        animeName = title,
        episode = 0,
        rating = "0",
        imageUrl = images.jpg?.imageUrl,
        youtubeId = url,
        youtubeUrl = "",
        year = 0,
        isTop = false,
        isRecommended = false
    )
}