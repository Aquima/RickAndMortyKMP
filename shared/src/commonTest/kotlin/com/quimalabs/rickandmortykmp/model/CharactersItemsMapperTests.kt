package com.quimalabs.rickandmortykmp.model
import com.quimalabs.rickandmortykmp.createResponseWithCode
import io.ktor.utils.io.charsets.Charsets
import io.ktor.utils.io.core.toByteArray
import kotlinx.serialization.json.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
class CharactersItemsMapperTests {
    @Test
    fun testMapThrowsErrorOn200HTTPResponseWithInvalidJSON() {
        val  invalidJson = "invalid json".toByteArray()
        assertFailsWith<CharacterItemsMapper.Error.InvalidData> {
            var response = createResponseWithCode(200)
            CharacterItemsMapper.map(invalidJson, response)
        }
    }
    @Test
    fun testMapThrowsErrorOnNon200HTTPResponse()  {
        val json = makeItemsJSON(listOf())
        val samples = listOf(199, 201, 300, 400, 500)
        samples.forEach { value ->
            assertFailsWith<CharacterItemsMapper.Error.InvalidData> {
                println("Value: $value")
                val response = createResponseWithCode(value)
                CharacterItemsMapper.map(json, response)
            }

        }
    }
    @Test
    fun testMapDeliversNoItemsOn200HTTPResponseWithEmptyJSONList() {
        val emptyListJson = makeItemsJSON(listOf())
        val result = CharacterItemsMapper.map(emptyListJson, createResponseWithCode(200))
        assertEquals(result, listOf())
    }
    @Test
    fun testMapDeliversItemsOn200HTTPResponseWithJSONItems() {
        val itemOne =  makeItem(
            id = 1,
            name = "Rick",
            status = Status.valueOf("Alive"),
            species = Species.valueOf("Human"),
            imageURL = "http://a_url.com"
        )
        val itemTwo =  makeItem(
            id = 1,
            name = "Morty",
            status = Status.valueOf("Alive"),
            species = Species.valueOf("Human"),
            imageURL = "http://another_url.com"
        )
        val json = makeItemsJSON(listOf(itemOne.second, itemTwo.second))

        val result = CharacterItemsMapper.map(json, createResponseWithCode(200))
        assertEquals(result.first().name, itemOne.first.name)
    }
    //region Helpers
    private fun makeItemsJSON(items: List<Map<String, Any>>): ByteArray {
        val json = buildJsonObject {
            putJsonArray("results") {
                items.forEach { item ->
                    addJsonObject {
                        item.forEach { (key, value) ->
                            // Assuming the value is always a primitive type or a String.
                            // Adjust this part as needed depending on the actual data structure.
                            put(key, value.toString())
                        }
                    }
                }
            }
        }
        return Json.encodeToString(JsonObject.serializer(), json).toByteArray(Charsets.UTF_8)
    }

    private fun makeItem(
        id: Long,
        name: String,
        status: Status,
        imageURL: String,
        species: Species = Species.Human
    ): Pair<RMCharacter, Map<String, Comparable<*>>> {
        val item = RMCharacter(id, name, status, species, imageURL)
        val json = mapOf(
            "id" to id,
            "name" to name,
            "status" to status.name,
            "image" to imageURL,
            "species" to species.name
        )
        return Pair(item, json)
    }
    //endregion
}