package com.quimalabs.rickandmortykmp.model
import kotlinx.serialization.*
import kotlinx.serialization.json.*


data class RMCharacters (
    val info: Info,
    val results: List<RMCharacter>
)

@Serializable
data class Info (
    val count: Long,
    val pages: Long,
    val next: String,
    val prev: JsonElement? = null
)

data class RMCharacter(
    val id: Long,
    val name: String,
    val status: Status,
    val species: Species,
    val image: String,

    )

@Serializable
enum class Gender(val value: String) {
    @SerialName("Female") Female("Female"),
    @SerialName("Male") Male("Male"),
    @SerialName("unknown") Unknown("unknown");
}

@Serializable
data class Location (
    val name: String,
    val url: String
)

@Serializable
enum class Species(val value: String) {
    @SerialName("Alien") Alien("Alien"),
    @SerialName("Human") Human("Human"),
    @SerialName("Humanoid") Humanoid("Humanoid"),
    @SerialName("Cronenberg") Cronenberg("Cronenberg");
    companion object {
        fun fromValue(value: String): Species = entries
            .firstOrNull { it.value.equals(value, ignoreCase = true) } ?: Species.Human
    }
}


enum class Status(val value: String) {
    Alive("Alive"),
    Dead("Dead"),
    Unknown("unknown");

    companion object {
        fun fromValue(value: String): Status = entries.firstOrNull { it.value.equals(value, ignoreCase = true) } ?: Unknown
    }
}
