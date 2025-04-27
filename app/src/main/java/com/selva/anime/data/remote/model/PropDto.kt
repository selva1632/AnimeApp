package com.selva.anime.data.remote.model

import com.google.gson.annotations.SerializedName

data class PropDto(
    @SerializedName("from") val from: FromDto,
    @SerializedName("to") val to: ToDto
)