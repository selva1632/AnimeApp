package com.selva.anime.presentation.data

sealed class VerticalItem {
    data class SliderItem(
        val data: List<HorizontalItem.SliderData>
    ) : VerticalItem()

    data class SuggestionItem(
        val data: List<HorizontalItem.NestedItem>,
        val id: Int,
        val title: String,
    ) : VerticalItem()
}