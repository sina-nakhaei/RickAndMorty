package com.example.rickandmorty.core.data.remotedatasource.characters

import com.example.rickandmorty.core.data.remotedatasource.characters.model.CharacterResponse
import com.example.rickandmorty.core.model.BasePaginatedResponse
import com.example.rickandmorty.core.model.NetworkResult
import com.example.rickandmorty.core.util.extensions.resultFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRemoteDataSourceDefault @Inject constructor(
    private val api: CharacterApi
) : CharacterRemoteDataSource {
    override fun getAllCharacters(): Flow<NetworkResult<BasePaginatedResponse<CharacterResponse>>> =
        resultFlow {
            val response = api.getAllCharacters()

            emit(NetworkResult.Success(response))
        }
}