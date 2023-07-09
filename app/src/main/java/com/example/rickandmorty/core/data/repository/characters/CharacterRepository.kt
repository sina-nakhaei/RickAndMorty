package com.example.rickandmorty.core.data.repository.characters

import com.example.rickandmorty.core.model.Result
import com.example.rickandmorty.core.ui.model.CharacterUiModel
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getAllCharacters(): Flow<Result<List<CharacterUiModel>>>
}