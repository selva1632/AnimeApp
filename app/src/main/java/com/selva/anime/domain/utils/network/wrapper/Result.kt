package com.selva.anime.domain.utils.network.wrapper

sealed class Result<T> {
    data class Error<T>(val message: String?) : Result<T>()
    data class Success<T>(val data: T) : Result<T>()
}