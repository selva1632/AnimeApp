package com.selva.anime.presentation.event

sealed class AnimeEvent {
    data class SelectAnime(val id: Int) : AnimeEvent()
}