package com.selva.anime.ui.home.contract

sealed class UiEvent {
    data class SelectAnime(val id: Int) : UiEvent()
}