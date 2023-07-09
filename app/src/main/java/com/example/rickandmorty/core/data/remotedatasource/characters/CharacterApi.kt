package com.example.rickandmorty.core.data.remotedatasource.characters

import com.example.rickandmorty.core.data.remotedatasource.characters.model.CharacterResponse
import com.example.rickandmorty.core.model.BasePaginatedResponse

interface CharacterApi {
    suspend fun getAllCharacters(): BasePaginatedResponse<CharacterResponse>
}