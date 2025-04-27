package com.selva.anime.data.remote.model

import com.google.gson.annotations.SerializedName

data class TitleDto(
    @SerializedName("title") val title: String,
    @SerializedName("type") val type: String
)