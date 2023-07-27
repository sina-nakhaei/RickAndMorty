package com.example.rickandmorty.core.data.repository.characters

import androidx.paging.PagingData
import com.example.rickandmorty.core.data.remotedatasource.characters.model.CharacterResponse
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getAllCharacters(): Flow<PagingData<CharacterResponse>>
}