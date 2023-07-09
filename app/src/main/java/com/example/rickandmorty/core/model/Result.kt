package com.example.rickandmorty.core.model

sealed interface Result<out T> {
    data class Success<T>(
        val data: T? = null
    ) : Result<T>

    data class Error(
        val message: String? = null,
        val type: String
    ) : Result<Nothing>

    object Loading : Result<Nothing>
}

inline fun <T> Result<T>.extractResult(
    block: (data: T?, error: Result.Error?, loading: Result.Loading?) -> Unit
) {
    when (this) {
        is Result.Loading -> block(null, null, this)
        is Result.Error -> block(null, this, null)
        is Result.Success -> block(this.data, null, null)
    }
}

fun <T> Result<T>.extractData(): T? =
    if (this is Result.Success)
        this.data
    else null

inline fun <T, R> Result<T>.map(crossinline transform: (value: T) -> R): Result<R> {
    return when (this) {
        is Result.Success -> Result.Success(transform(data!!))
        is Result.Error -> this
        is Result.Loading -> this
    }
}