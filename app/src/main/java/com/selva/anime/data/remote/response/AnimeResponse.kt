package com.selva.anime.data.remote.response

import com.google.gson.annotations.SerializedName
import com.selva.anime.data.remote.model.DataDto
import com.selva.anime.data.remote.model.PaginationDto

data class AnimeResponse(
    @SerializedName("data") val data: List<DataDto>,
    @SerializedName("pagination") val pagination: PaginationDto
)