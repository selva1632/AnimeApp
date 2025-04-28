package com.selva.anime.data.local.mapper

import com.selva.anime.data.local.model.AnimeEntity
import com.selva.anime.domain.model.AnimeItem

fun AnimeEntity.toAnimeItem(): AnimeItem {
    return AnimeItem(
        id = animeId,
        title = animeName,
        episode =  episode,
        rating = rating,
        imageUrl = imageUrl,
        youtubeUrl = youtubeUrl,
        youtubeId = youtubeId,
        url = url,
        isSelected = false
    )
}