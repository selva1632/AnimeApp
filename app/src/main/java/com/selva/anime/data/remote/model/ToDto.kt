package com.selva.anime.data.remote.model

import com.google.gson.annotations.SerializedName

data class ToDto(
    @SerializedName("day") val day: Int,
    @SerializedName("month") val month: Int,
    @SerializedName("year") val year: Int
)