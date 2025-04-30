package com.selva.anime.presentation.data

sealed class HorizontalItem {
    data class SliderData(
        val contentDescription: String?,
        val imageUrl: String?
    )

    data class NestedItem(
        val id: Int,
        val contentDescription: String?,
        val imageUrl: String?,
        val youtubeId: String?,
        val youtubeUrl: String?,
        val url: String?
    )
}