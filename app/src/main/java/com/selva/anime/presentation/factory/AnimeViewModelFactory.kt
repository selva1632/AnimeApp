package com.selva.anime.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.selva.anime.presentation.model.AnimeModel
import com.selva.anime.presentation.HomeViewmodel

class AnimeViewModelFactory(private val model: AnimeModel) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewmodel(
            model
        ) as T
    }
}