package com.example.rickandmorty.core.data.remotedatasource.characters

import com.example.rickandmorty.core.data.remotedatasource.characters.model.CharacterResponse
import com.example.rickandmorty.core.model.ApiEndpoints
import com.example.rickandmorty.core.model.BasePaginatedResponse
import com.example.rickandmorty.core.model.Result
import com.example.rickandmorty.core.util.extensions.resultFlow
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRemoteDataSourceDefault @Inject constructor(
    private val client: HttpClient
) : CharacterRemoteDataSource {
    override fun getAllCharacters(): Flow<Result<BasePaginatedResponse<CharacterResponse>>> =
        resultFlow {
            val response: BasePaginatedResponse<CharacterResponse> =
                client.get(ApiEndpoints.CHARACTERS).body()

            emit(Result.Success(response))
        }
}