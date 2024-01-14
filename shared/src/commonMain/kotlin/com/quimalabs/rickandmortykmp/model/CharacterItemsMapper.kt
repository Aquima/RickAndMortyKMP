package com.quimalabs.rickandmortykmp.model
import io.ktor.client.request.HttpResponseData
import kotlinx.serialization.*
import kotlinx.serialization.json.*


class CharacterItemsMapper {
    @Serializable
    data class RemoteCharacterItem(
        val id: Long,
        val name: String,
        val status: String,
        val image: String,
        val species: String
    )

    @Serializable
    data class Root(
        private val results: List<RemoteCharacterItem>
    ) {
        val characters: List<RMCharacter>
            get() = results.map {
                RMCharacter(
                    id = it.id,
                    name = it.name,
                    status = Status.fromValue(it.status) ,
                    species = Species.valueOf(it.species),
                    image = it.image,
                )
            }
    }


    sealed class Error : Throwable() {
        object InvalidData : Error()
    }
    companion object {
        fun map(data: ByteArray, response: HttpResponseData): List<RMCharacter> {
            if (response.statusCode.value!= 200) {
                throw Error.InvalidData
            }
            val jsonString = data.decodeToString()
            return try {
                val root = Json.decodeFromString<Root>(jsonString)
                root.characters
            } catch (e: SerializationException) {
                throw Error.InvalidData
            }
        }
    }

}
