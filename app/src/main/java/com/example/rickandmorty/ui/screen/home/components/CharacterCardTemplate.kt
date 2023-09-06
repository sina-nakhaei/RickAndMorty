package com.example.rickandmorty.ui.screen.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CharacterCardTemplate(
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .size(250.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        content()
    }
}