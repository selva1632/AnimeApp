package com.selva.anime.data.remote.model

import com.google.gson.annotations.SerializedName

data class PaginationDto(
    @SerializedName("current_page") val current_page: Int,
    @SerializedName("has_next_page") val has_next_page: Boolean,
    @SerializedName("items") val items: ItemsDto,
    @SerializedName("last_visible_page") val last_visible_page: Int
)