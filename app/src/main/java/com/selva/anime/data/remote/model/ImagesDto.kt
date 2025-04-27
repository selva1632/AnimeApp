package com.selva.anime.data.remote.model

import com.google.gson.annotations.SerializedName

data class ImagesDto(
    @SerializedName("jpg") val jpg: JpgDto?,
    @SerializedName("webp") val webp: WebpDto?
)