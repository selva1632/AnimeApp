package com.selva.anime.ui.home.contract

sealed class UiState<out T> {
    data class Error(val error: String) : UiState<Nothing>()
    data object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
}