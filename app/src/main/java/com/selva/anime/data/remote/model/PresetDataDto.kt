package com.selva.anime.data.remote.model

import com.google.gson.annotations.SerializedName

data class PresetDataDto(
    @SerializedName("mal_id") val malId: String,
    @SerializedName("entry") val  entry: List<EntryDto>,
    @SerializedName("content") val content: String,
    @SerializedName("date") val date: String,
    @SerializedName("user") val user: UserDto
)