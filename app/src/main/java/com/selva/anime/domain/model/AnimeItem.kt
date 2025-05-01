package com.selva.anime.domain.model

data class AnimeItem(
    val episode: Int?,
    val id: Int?,
    val isSelected: Boolean,
    val imageUrl: String?,
    val rating: String?,
    val title: String?,
    val url: String?,
    val youtubeUrl: String?,
    val youtubeId: String?,
)
