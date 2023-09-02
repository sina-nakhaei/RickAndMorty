package com.example.rickandmorty.core.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<T : BaseUiState> : ViewModel() {
    abstract val uiState: StateFlow<T>
}