package com.example.rickandmorty.ui.screen.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.rickandmorty.core.ui.components.BlinkingContainer
import com.example.rickandmorty.core.ui.model.CharacterUiModel
import com.example.rickandmorty.ui.screen.home.components.CharacterCard
import com.example.rickandmorty.ui.screen.home.components.CharacterCardLoadingPlaceholder

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF439AC2))
    ) {
        when (val uiState = viewModel.uiState.collectAsState().value) {
            is Loading -> Loading()
            is Success -> Container(uiState.characters.collectAsLazyPagingItems())
        }
    }
}

@Composable
private fun Loading() {
    val list = Array(30) { "" }
    BlinkingContainer {
        GridTemplate {
            items(list.size) {
                CharacterCardLoadingPlaceholder()
            }
        }
    }
}

@Composable
private fun Container(characters: LazyPagingItems<CharacterUiModel>) {
    when (characters.loadState.refresh) {
        is LoadState.Loading -> Loading()
        is LoadState.NotLoading -> CharactersGrid(characters)
        else -> Error(onClick = { characters.retry() })
    }
}

@Composable
private fun CharactersGrid(characters: LazyPagingItems<CharacterUiModel>) {
    GridTemplate {
        items(characters.itemCount) { index ->
            characters[index]?.let { character ->
                val context = LocalContext.current
                CharacterCard(
                    onClick = {
                        Toast.makeText(context, character.name, Toast.LENGTH_SHORT).show()
                    },
                    character = character
                )
            }
        }
    }
}

@Composable
private fun GridTemplate(
    content: LazyGridScope.() -> Unit
) {
    LazyVerticalGrid(
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        columns = GridCells.Fixed(2)
    ) {
        content()
    }
}

@Composable
private fun Error(onClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onClick) {
            Text(text = "Try Again!")
        }
    }
}