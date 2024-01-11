package com.quimalabs.rickandmortykmp.model
import kotlin.test.Test
import kotlin.test.assertEquals
import io.ktor.http.*

class CharactersEndPointTests {

    @Test
    fun testCharactersEndpointURL() {
        val baseURL = Url("http://any_base_url.com")

        val received = CharactersEndPoint.GET.url(baseURL)
        assertEquals("http", received.protocol.name, "scheme")
        assertEquals("any_base_url.com", received.host, "host")
        assertEquals("/character", received.encodedPath, "path")
        assertEquals("1", received.parameters.get("page"), "query")
    }

    @Test
    fun testCharactersEndpointURLAfterGivenImage() {
        val page = 1
        val baseURL = Url("http://any_base_url.com")

        val received = CharactersEndPoint.GET.url(baseURL, page, "rick")

        assertEquals("http", received.protocol.name, "scheme")
        assertEquals("any_base_url.com", received.host, "host")
        assertEquals("/character", received.encodedPath, "path")
        val query = received.parameters.entries().joinToString("&") { "${it.key}=${it.value.first()}" }
        assertEquals(true, query.contains("page=1"), "page query param")
        assertEquals(true, query.contains("name=rick"), "name query param")
    }
}