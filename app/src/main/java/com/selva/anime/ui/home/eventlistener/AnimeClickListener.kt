package com.selva.anime.ui.home.eventlistener

import com.selva.anime.presentation.event.AnimeEvent

interface AnimeClickListener {
    fun sendEvent(event: AnimeEvent)
}