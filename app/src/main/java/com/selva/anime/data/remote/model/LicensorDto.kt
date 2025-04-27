package com.selva.anime.data.remote.model

import com.google.gson.annotations.SerializedName

data class LicensorDto(
    @SerializedName("mal_id") val malId: Int,
    @SerializedName("name") val name: String,
    @SerializedName("type") val type: String,
    @SerializedName("url") val url: String
)