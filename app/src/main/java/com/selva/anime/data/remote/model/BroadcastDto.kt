package com.selva.anime.data.remote.model

import com.google.gson.annotations.SerializedName

data class BroadcastDto(
    @SerializedName("day") val day: Any,
    @SerializedName("string") val string: Any,
    @SerializedName("time") val time: Any,
    @SerializedName("timezone") val timezone: Any
)