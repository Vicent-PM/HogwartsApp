package com.example.hogwarts.data.models.getSpells


import com.squareup.moshi.Json

data class Spells(
    @Json(name = "id")
    val id: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "description")
    val description: String?
)