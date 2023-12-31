package com.example.rickandmorty.core.data.remotedatasource.characters

import com.example.rickandmorty.core.data.remotedatasource.characters.model.CharacterResponse
import com.example.rickandmorty.core.model.ApiEndpoints
import com.example.rickandmorty.core.model.BasePaginatedResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class CharacterApiDefault @Inject constructor(
    private val client: HttpClient
) : CharacterApi {
    override suspend fun getAllCharacters(page: Int): BasePaginatedResponse<CharacterResponse> =
        client.get(ApiEndpoints.CHARACTERS) {
            parameter("page", page)
        }.body()
}