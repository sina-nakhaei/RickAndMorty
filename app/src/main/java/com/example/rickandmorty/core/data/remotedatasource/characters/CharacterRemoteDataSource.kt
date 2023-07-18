package com.example.rickandmorty.core.data.remotedatasource.characters

import com.example.rickandmorty.core.data.remotedatasource.characters.model.CharacterResponse
import com.example.rickandmorty.core.model.BasePaginatedResponse
import com.example.rickandmorty.core.model.NetworkResult
import kotlinx.coroutines.flow.Flow

interface CharacterRemoteDataSource {
    fun getAllCharacters(page: Int): Flow<NetworkResult<BasePaginatedResponse<CharacterResponse>>>
}