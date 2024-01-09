package com.quimalabs.rickandmortykmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform