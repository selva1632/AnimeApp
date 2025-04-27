package com.selva.anime.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.selva.anime.domain.usecase.AnimeUseCase
import com.selva.anime.presentation.AnimeModel
import com.selva.anime.presentation.viewmodel.AnimeViewmodel

class AnimeViewModelFactory(private val model: AnimeModel) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AnimeViewmodel(
            model
        ) as T
    }
}