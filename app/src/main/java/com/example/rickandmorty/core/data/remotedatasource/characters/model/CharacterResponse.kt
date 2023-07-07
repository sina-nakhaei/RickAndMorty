package com.example.rickandmorty.core.data.remotedatasource.characters.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    @SerialName("id")
    val id: Int,

    @SerialName("name")
    val name: String,

    @SerialName("status")
    val status: String,

    @SerialName("species")
    val species: String,

    @SerialName("type")
    val type: String,

    @SerialName("gender")
    val gender: String,

    @SerialName("origin")
    val origin: Origin,

    @SerialName("location")
    val location: Location,

    @SerialName("image")
    val image: String,

    @SerialName("episode")
    val episode: List<String>,

    @SerialName("url")
    val url: String,

    @SerialName("created")
    val created: String,
)

@Serializable
data class Origin(
    @SerialName("name")
    val name: String,

    @SerialName("url")
    val url: String
)

@Serializable
data class Location(
    @SerialName("name")
    val name: String,

    @SerialName("url")
    val url: String
)