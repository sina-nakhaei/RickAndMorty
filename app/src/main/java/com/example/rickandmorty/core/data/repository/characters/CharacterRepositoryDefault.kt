package com.example.rickandmorty.core.data.repository.characters

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmorty.core.data.pagingsource.characters.CharacterPagingSource
import com.example.rickandmorty.core.data.remotedatasource.characters.CharacterRemoteDataSource
import com.example.rickandmorty.core.data.remotedatasource.characters.model.CharacterResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRepositoryDefault @Inject constructor(
    private val remoteDataSource: CharacterRemoteDataSource
) : CharacterRepository {
    private val characters = Pager(config = PagingConfig(20)) {
        CharacterPagingSource(remoteDataSource)
    }

    override fun getAllCharacters(): Flow<PagingData<CharacterResponse>> =
        characters.flow
}
