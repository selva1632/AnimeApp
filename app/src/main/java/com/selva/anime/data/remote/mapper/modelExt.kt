package com.selva.anime.data.remote.mapper

import com.selva.anime.data.local.model.AnimeEntity
import com.selva.anime.data.remote.model.DataDto
import com.selva.anime.data.remote.model.EntryDto
import com.selva.anime.domain.model.AnimeItem

fun DataDto.toAnimeItem(): AnimeItem {
    return AnimeItem(
        episode = episodes,
        id = malId,
        imageUrl = images?.jpg?.imageUrl,
        isSelected = false,
        rating = rating,
        title = title,
        url = url,
        youtubeId = trailer?.youtubeId,
        youtubeUrl = trailer?.url
    )
}

fun DataDto.toAnimeEntity(): AnimeEntity {
    return AnimeEntity(
        animeId = malId,
        animeName = title,
        episode = episodes,
        imageUrl = images?.jpg?.imageUrl,
        isRecommended = false,
        isTop = false,
        url = url,
        rating = rating,
        year = year,
        youtubeUrl = trailer?.url,
        youtubeId = trailer?.youtubeId,
    )
}

fun EntryDto.toAnimeItem(): AnimeItem {
    return AnimeItem(
        episode = 0,
        id = malId,
        imageUrl = images.jpg?.imageUrl,
        isSelected = false,
        rating = "0",
        title = title,
        url = url,
        youtubeId = "",
        youtubeUrl = url
    )
}

fun EntryDto.toAnimeEntity(): AnimeEntity {
    return AnimeEntity(
        animeId = malId,
        animeName = title,
        episode = 0,
        imageUrl = images.jpg?.imageUrl,
        isRecommended = false,
        isTop = false,
        rating = "0",
        year = 0,
        youtubeId = url,
        youtubeUrl = ""
    )
}