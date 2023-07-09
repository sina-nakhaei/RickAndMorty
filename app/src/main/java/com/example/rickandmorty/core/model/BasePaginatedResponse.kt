package com.example.rickandmorty.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BasePaginatedResponse<T>(
    @SerialName("info")
    val info: Info,

    @SerialName("results")
    val results: List<T>
)

@Serializable
data class Info(
    @SerialName("count")
    val count: Int,

    @SerialName("pages")
    val pages: Int,

    @SerialName("next")
    val next: String?,

    @SerialName("prev")
    val prev: String?
)

inline fun <T, R> BasePaginatedResponse<T>.extractAndMapResult(
    crossinline transform: (T) -> R
): List<R> {
    return this.results.map(transform)
}