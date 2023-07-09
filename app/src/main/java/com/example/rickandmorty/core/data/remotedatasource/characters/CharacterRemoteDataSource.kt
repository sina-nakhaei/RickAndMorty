package com.example.rickandmorty.core.data.remotedatasource.characters

import com.example.rickandmorty.core.data.remotedatasource.characters.model.CharacterResponse
import com.example.rickandmorty.core.model.BasePaginatedResponse
import com.example.rickandmorty.core.model.Result
import kotlinx.coroutines.flow.Flow

interface CharacterRemoteDataSource {
    fun getAllCharacters(): Flow<Result<BasePaginatedResponse<CharacterResponse>>>
}