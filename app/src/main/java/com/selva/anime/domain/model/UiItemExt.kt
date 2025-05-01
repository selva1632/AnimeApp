package com.selva.anime.domain.model

import com.selva.anime.presentation.data.DetailData
import com.selva.anime.presentation.data.HorizontalItem

fun AnimeItem.toSlider(): HorizontalItem.SliderData {
    return HorizontalItem.SliderData(
        contentDescription = title,
        imageUrl = imageUrl
    )
}

fun AnimeItem.toSeasons(): HorizontalItem.NestedItem {
    return HorizontalItem.NestedItem(
        contentDescription = title,
        id = id ?: 0,
        imageUrl = imageUrl,
        url = url,
        youtubeUrl = youtubeUrl,
        youtubeId = youtubeId,
    )
}

fun AnimeItem.toDetail(): DetailData {
    return DetailData(
        id = id ?: 0,
        url = url,
        youtubeId = youtubeId,
        youtubeUrl = youtubeUrl
    )
}