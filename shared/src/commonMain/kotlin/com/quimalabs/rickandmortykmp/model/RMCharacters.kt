package com.quimalabs.rickandmortykmp.model

data class RMCharacter(
    val id: Long,
    val name: String,
    val status: Status,
    val species: Species,
    val image: String,

    )

enum class Gender(val value: String) {
    Female("Female"),
    Male("Male"),
    Unknown("unknown");
}

data class Location (
    val name: String,
    val url: String
)

enum class Species(val value: String) {
    Alien("Alien"),
    Human("Human"),
    Humanoid("Humanoid"),
    Cronenberg("Cronenberg");
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
