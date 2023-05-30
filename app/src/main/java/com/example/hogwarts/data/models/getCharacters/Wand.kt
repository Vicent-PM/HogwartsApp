package com.example.hogwarts.data.models.getCharacters


import com.squareup.moshi.Json

data class Wand(
    @Json(name = "wood")
    val wood: String?,
    @Json(name = "core")
    val core: String?,
    @Json(name = "length")
    val length: Double?
)