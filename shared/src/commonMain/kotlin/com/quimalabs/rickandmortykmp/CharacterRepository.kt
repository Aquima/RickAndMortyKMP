package com.quimalabs.rickandmortykmp

import co.touchlab.kermit.Logger
import com.quimalabs.rickandmortykmp.model.CharacterItemsMapper
import com.quimalabs.rickandmortykmp.model.CharactersEndPoint
import com.quimalabs.rickandmortykmp.model.RMCharacter
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.Url
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json

class CharacterRepository {
    private val client = HttpClient() {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }
    private val baseURL = Url("https://rickandmortyapi.com/")

    suspend fun getCharacter(page: Int, name:String): Flow<CharactersRequestState>{
        val url = CharactersEndPoint.GET.url(baseURL, page, name)
        println(url)
        return flow {
            emit(CharactersRequestState.Loading)
//            delay(2000)
            try {
                emit(
                    CharactersRequestState.Success(
                        data =  client.get(url).body()
                    )
                )
            } catch (e: Exception) {
                Logger.setTag("Characters")
                Logger.e { e.message.toString() }
                emit(CharactersRequestState.Error(message = "Error while fetching the data."))
            }
        }
    }
}
