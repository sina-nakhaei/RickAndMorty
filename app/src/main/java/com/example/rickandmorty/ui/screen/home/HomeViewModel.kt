package com.example.rickandmorty.ui.screen.home

import com.example.rickandmorty.core.base.BaseViewModel
import com.example.rickandmorty.core.data.remotedatasource.characters.model.asUiModel
import com.example.rickandmorty.core.data.repository.characters.CharacterRepository
import com.example.rickandmorty.core.util.extensions.mapPagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    repository: CharacterRepository
) : BaseViewModel<HomeUiState>() {
    private val _uiState = MutableStateFlow<HomeUiState>(Loading)
    override val uiState: StateFlow<HomeUiState>
        get() = _uiState

    init {
        repository
            .getAllCharacters()
            .mapPagingData { response -> response.asUiModel() }
            .let {
                _uiState.value = Success(it)
            }
    }
}