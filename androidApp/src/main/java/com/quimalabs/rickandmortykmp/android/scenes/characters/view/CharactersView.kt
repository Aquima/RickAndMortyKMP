package com.quimalabs.rickandmortykmp.android.scenes.characters.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.quimalabs.rickandmortykmp.android.scenes.characters.viewmodel.CharactersViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.quimalabs.rickandmortykmp.CharactersRequestState

@Preview
@Composable
fun CharactersView() {
    val charactersViewModel: CharactersViewModel = viewModel()
    val characterRequestState: CharactersRequestState by charactersViewModel.charactersRequestState

    if(characterRequestState.isLoading()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else if (characterRequestState.isSuccess()) {
        LazyColumn(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(
                items = characterRequestState.getCharacters(),
                key = { it.id }
            ) {
              CharacterItemCard(it)
            }
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(all = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Error: not loading",// characterRequestState.getErrorMessage(),
                textAlign = TextAlign.Center
            )
        }
    }
}


