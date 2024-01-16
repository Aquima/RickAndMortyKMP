package com.quimalabs.rickandmortykmp

import com.quimalabs.rickandmortykmp.model.CharacterItemsMapper
import com.quimalabs.rickandmortykmp.model.RMCharacter
import kotlinx.serialization.Serializable

@Serializable
sealed class CharactersRequestState {
    @Serializable
    data object Idle : CharactersRequestState()

    @Serializable
    data object Loading : CharactersRequestState()

    @Serializable
    data class Success(val data: CharacterItemsMapper.Root) : CharactersRequestState()

    @Serializable
    data class Error(val message: String) : CharactersRequestState()

    fun isLoading(): Boolean = this is Loading
    fun isSuccess(): Boolean = this is Success
    fun isError(): Boolean = this is Error

    fun getCharacters(): List<RMCharacter> = (this as Success).data.characters

    fun getErrorMessage(): String = (this as Error).message
}