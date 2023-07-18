package com.example.rickandmorty.core.data.repository.characters

import com.example.rickandmorty.core.data.remotedatasource.characters.CharacterRemoteDataSource
import com.example.rickandmorty.core.data.remotedatasource.characters.model.asUiModel
import com.example.rickandmorty.core.model.NetworkResult
import com.example.rickandmorty.core.model.extractAndMapResult
import com.example.rickandmorty.core.model.map
import com.example.rickandmorty.core.ui.model.CharacterUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharacterRepositoryDefault @Inject constructor(
    private val remoteDataSource: CharacterRemoteDataSource
) : CharacterRepository {

    override fun getAllCharacters(page: Int): Flow<NetworkResult<List<CharacterUiModel>>> =
        getAllCharactersFromRemote(page)

    private fun getAllCharactersFromRemote(page: Int) = remoteDataSource
        .getAllCharacters(page)
        .map { result ->
            result.map { response ->
                response.extractAndMapResult { character ->
                    character.asUiModel()
                }
            }
        }
}
