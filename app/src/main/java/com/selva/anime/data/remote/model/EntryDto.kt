package com.selva.anime.data.remote.model

import com.google.gson.annotations.SerializedName

data class EntryDto(
    @SerializedName("mal_id") val malId: Int,
    @SerializedName("url") val url: String,
    @SerializedName("images") val images: ImagesDto,
    @SerializedName("title") val title: String
)