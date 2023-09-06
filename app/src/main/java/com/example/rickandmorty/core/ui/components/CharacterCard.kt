package com.example.rickandmorty.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
    MainContainer(
        onClick = { onClick(character.id) },
        modifier = modifier
    ) {
        CharacterImage(character.image)
        Name(character.name)
    }
}

@Composable
private fun MainContainer(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .clickable {
                onClick()
            }
            .size(250.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        content()
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