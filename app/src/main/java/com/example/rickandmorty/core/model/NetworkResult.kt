package com.example.rickandmorty.core.model

sealed interface NetworkResult<out T> {
    data class Success<T>(
        val data: T? = null
    ) : NetworkResult<T>

    data class Error(
        val message: String? = null,
        val throwable: Throwable? = null
    ) : NetworkResult<Nothing>

    object Loading : NetworkResult<Nothing>
}

fun <T> NetworkResult<T>.extractData(): T? =
    if (this is NetworkResult.Success)
        this.data
    else null