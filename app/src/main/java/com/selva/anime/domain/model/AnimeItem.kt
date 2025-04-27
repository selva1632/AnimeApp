package com.selva.anime.domain.model

data class AnimeItem(
    val id: Int?,
    val title: String?,
    val episode: Int?,
    val rating: String?,
    val imageUrl: String?,
    val youtubeUrl: String?,
    val youtubeId: String?,
    val isSelected: Boolean
)
