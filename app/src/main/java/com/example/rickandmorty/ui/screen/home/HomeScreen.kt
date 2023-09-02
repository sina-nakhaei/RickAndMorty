package com.example.rickandmorty.ui.screen.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.log
import com.example.rickandmorty.core.ui.model.CharacterUiModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    uiState: HomeUiState = viewModel.uiState.value
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF439AC2))
    ) {
        Log.d("sinatest", "HomeScreen: $uiState")
        when (uiState) {
            is Loading -> {
                Loading()
            }

            is Success -> {
                Container(uiState.characters.collectAsLazyPagingItems())
            }
        }
    }
}

@Composable
private fun Loading() {
    Column(
        modifier = Modifier
            .size(40.dp)
            .background(Color.Black)
    ) {

    }
}

@Composable
private fun Container(characters: LazyPagingItems<CharacterUiModel>) {

    val state = characters.loadState.refresh
    Log.d("sinatest","state: $state")
    when(state){
        is LoadState.Loading -> Loading()
        is LoadState.NotLoading -> CharactersColumn(characters)
        else -> {
            Text(text = "Error")
        }
    }

}

@Composable
private fun CharactersColumn(characters: LazyPagingItems<CharacterUiModel>) {
    LazyColumn {
        items(
            count = characters.itemCount,
            contentType = characters.itemContentType()
        ) { index ->
            characters[index]?.let {
                Text(text = it.name)
            }
        }
    }
}