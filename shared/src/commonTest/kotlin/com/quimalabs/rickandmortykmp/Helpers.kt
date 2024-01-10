package com.quimalabs.rickandmortykmp

import io.ktor.client.request.HttpResponseData
import io.ktor.http.ContentType
import io.ktor.http.HttpProtocolVersion
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.util.date.GMTDate
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.core.toByteArray
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers

fun createResponseWithCode(code: Int): HttpResponseData {
    val customContext = Dispatchers.Default + CoroutineName("MyCoroutine")
    val mockResponseData = HttpResponseData(
        HttpStatusCode(value = code,""),
        GMTDate(),
        headersOf("Content-Type" to listOf(ContentType.Application.Json.toString())),
        HttpProtocolVersion.HTTP_1_1,
        ByteReadChannel("Mock response body".toByteArray()),
        customContext
    )
    return  mockResponseData
}