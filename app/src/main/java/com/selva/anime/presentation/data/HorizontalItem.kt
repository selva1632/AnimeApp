package com.selva.anime.presentation.data

sealed class HorizontalItem {
    data class SliderData(
        val contentDescription: String?,
        val imageUrl: String?
    )

    data class NestedItem(
        val contentDescription: String?,
        val id: Int,
        val imageUrl: String?,
        val url: String?,
        val youtubeId: String?,
        val youtubeUrl: String?
    )
}