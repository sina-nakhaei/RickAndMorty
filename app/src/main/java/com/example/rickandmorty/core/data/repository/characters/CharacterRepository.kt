package com.example.rickandmorty.core.data.repository.characters

import com.example.rickandmorty.core.model.NetworkResult
import com.example.rickandmorty.core.ui.model.CharacterUiModel
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getAllCharacters(): Flow<NetworkResult<List<CharacterUiModel>>>
}