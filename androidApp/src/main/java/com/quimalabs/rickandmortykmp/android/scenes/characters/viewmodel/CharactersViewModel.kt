package com.quimalabs.rickandmortykmp.android.scenes.characters.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quimalabs.rickandmortykmp.CharacterRepository
import com.quimalabs.rickandmortykmp.CharactersRequestState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CharactersViewModel: ViewModel() {
    private var _requestState: MutableState<CharactersRequestState> = mutableStateOf(CharactersRequestState.Idle)
    val charactersRequestState: State<CharactersRequestState> = _requestState
    init {
        viewModelScope.launch(Dispatchers.Main) {
            CharacterRepository().getCharacter(1,"rick").collectLatest {
                _requestState.value = it
            }
        }
    }
}