package com.selva.anime.data.remote.model

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("url") val url: String,
    @SerializedName("username") val username: String
)