package com.example.rickandmorty.core.model

sealed interface NetworkResult<out T> {
    data class Success<T>(
        val data: T? = null
    ) : NetworkResult<T>

    data class Error(
        val message: String? = null,
        val type: String
    ) : NetworkResult<Nothing>

    object Loading : NetworkResult<Nothing>
}

inline fun <T> NetworkResult<T>.extractResult(
    block: (data: T?, error: NetworkResult.Error?, loading: NetworkResult.Loading?) -> Unit
) {
    when (this) {
        is NetworkResult.Loading -> block(null, null, this)
        is NetworkResult.Error -> block(null, this, null)
        is NetworkResult.Success -> block(this.data, null, null)
    }
}

fun <T> NetworkResult<T>.extractData(): T? =
    if (this is NetworkResult.Success)
        this.data
    else null

inline fun <T, R> NetworkResult<T>.map(crossinline transform: (value: T) -> R): NetworkResult<R> {
    return when (this) {
        is NetworkResult.Success -> NetworkResult.Success(transform(data!!))
        is NetworkResult.Error -> this
        is NetworkResult.Loading -> this
    }
}