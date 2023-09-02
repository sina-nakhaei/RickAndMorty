package com.example.rickandmorty.ui.screen.home

import androidx.paging.PagingData
import com.example.rickandmorty.core.base.BaseUiState
import com.example.rickandmorty.core.ui.model.CharacterUiModel
import kotlinx.coroutines.flow.Flow

interface HomeUiState : BaseUiState
object Loading : HomeUiState

data class Success(
    val characters: Flow<PagingData<CharacterUiModel>>
) : HomeUiState