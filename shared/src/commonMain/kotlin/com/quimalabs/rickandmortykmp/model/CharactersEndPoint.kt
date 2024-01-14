package com.quimalabs.rickandmortykmp.model
import io.ktor.http.URLBuilder
import io.ktor.http.Url
import io.ktor.http.path

enum class CharactersEndPoint {
    GET;

    fun url(baseURL: Url, page: Int = 1, name: String = ""): Url {
        return when (this) {
            GET -> {
                val urlBuilder = URLBuilder(baseURL)
                urlBuilder.path("api/character/")
                if (page != 0) {
                    urlBuilder.parameters.append("page", page.toString())
                }
                if (name.isNotEmpty()) {
                    urlBuilder.parameters.append("name", name)
                }
                urlBuilder.build()
            }
        }
    }
}
