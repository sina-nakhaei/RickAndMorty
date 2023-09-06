package com.example.rickandmorty.ui.screen.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.rickandmorty.core.ui.model.CharacterUiModel

@Composable
fun CharacterCard(
    onClick: (id: Int) -> Unit,
    character: CharacterUiModel,
    modifier: Modifier = Modifier
) {
    CharacterCardTemplate(
        modifier = modifier
            .clickable { onClick(character.id) }
    ) {
        CharacterImage(character.image)
        Name(character.name)
    }
}

@Composable
private fun CharacterImage(url: String) {
    Image(
        modifier = Modifier.size(200.dp),
        painter = rememberAsyncImagePainter(model = url),
        contentScale = ContentScale.Crop,
        contentDescription = null
    )
}

@Composable
private fun ColumnScope.Name(name: String) {
    Column(
        modifier = Modifier.Companion.weight(1f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}