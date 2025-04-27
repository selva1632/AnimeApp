package com.selva.anime.data.remote.response

import com.google.gson.annotations.SerializedName
import com.selva.anime.data.remote.model.PaginationDto
import com.selva.anime.data.remote.model.PresetDataDto

data class PresetResponse(
    @SerializedName("data") val data: List<PresetDataDto>,
    @SerializedName("pagination") val pagination: PaginationDto
)