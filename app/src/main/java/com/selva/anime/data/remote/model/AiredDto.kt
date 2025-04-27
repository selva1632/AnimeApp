package com.selva.anime.data.remote.model

import com.google.gson.annotations.SerializedName

data class AiredDto(
    @SerializedName("from") val from: String,
    @SerializedName("prop") val prop: PropDto,
    @SerializedName("string") val string: String,
    @SerializedName("to") val to: String
)