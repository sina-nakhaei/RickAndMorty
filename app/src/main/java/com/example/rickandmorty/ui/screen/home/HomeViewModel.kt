package com.example.rickandmorty.ui.screen.home

import androidx.lifecycle.ViewModel
import com.example.rickandmorty.core.data.repository.characters.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    repository: CharacterRepository
) : ViewModel() {

    val characters = repository.getAllCharacters()

}