package com.quimalabs.rickandmortykmp

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import kotlinx.coroutines.flow.collect
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class CharactersRepositoryTests {
    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope()
    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)

    }
    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
        testScope.cancel()
    }
    @Test
    fun someFunction() = testScope.runTest {
        val repository: CharacterRepository = CharacterRepository()
            repository.getCharacter(1, "Rick").collect { state ->
                println(state)
                when (state) {
                    is CharactersRequestState.Loading -> {
                        // Handle loading state
                    }
                    is CharactersRequestState.Success -> {
                        // Handle success state
                        println("Handle success state")
                        val result = state.data
                        println(result.characters.size)
                        // Do something with characters
                        assertTrue(result.characters.size > 0,"Looks good")
                    }
                    is CharactersRequestState.Error -> {
                        // Handle error state
                        val error = state.message
                        // Handle the error
                    }

                    else -> {
                        println("some error")
                    }
                }
            }

    }
}

