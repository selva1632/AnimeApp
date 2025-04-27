package com.selva.anime.data.remote.model

import com.google.gson.annotations.SerializedName

data class ItemsDto(
    @SerializedName("count") val count: Int,
    @SerializedName("per_page") val perPage: Int,
    @SerializedName("total") val total: Int
)