package com.selva.anime.data.remote.model

import com.google.gson.annotations.SerializedName

data class TrailerDto(
    @SerializedName("embed_url") val embedUrl: String,
    @SerializedName("images") val images: ImagesXDto,
    @SerializedName("url") val url: String,
    @SerializedName("youtube_id") val youtubeId: String
)