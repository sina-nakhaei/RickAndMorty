package com.example.rickandmorty.core.model

sealed interface Result<out T> {
    data class Success<T>(
        val data: T? = null
    ) : Result<T>

    data class Error(
        val message: String? = null,
        val type: String
    ) : Result<Unit>

    object Loading : Result<Unit>
}