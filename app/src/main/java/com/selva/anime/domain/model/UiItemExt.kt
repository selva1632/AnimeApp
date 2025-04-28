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
        id = id,
        contentDescription = title,
        imageUrl = imageUrl,
        youtubeUrl = youtubeUrl,
        youtubeId = youtubeId,
        url = url
    )
}

fun AnimeItem.toDetail(): DetailData {
    return DetailData(
        url = url,
        youtubeId = youtubeId,
        youtubeUrl = youtubeUrl
    )
}