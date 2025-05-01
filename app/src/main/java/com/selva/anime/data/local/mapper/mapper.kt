package com.selva.anime.data.local.mapper

import com.selva.anime.data.local.model.AnimeEntity
import com.selva.anime.domain.model.AnimeItem

fun AnimeEntity.toAnimeItem(): AnimeItem {
    return AnimeItem(
        episode =  episode,
        id = animeId,
        isSelected = false,
        rating = rating,
        title = animeName,
        imageUrl = imageUrl,
        youtubeUrl = youtubeUrl,
        youtubeId = youtubeId,
        url = url
    )
}