package com.example.hogwarts.data.models.getCharacters


import com.squareup.moshi.Json

data class Characters(
    @Json(name = "id")
    val id: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "alternate_names")
    val alternateNames: List<String?>?,
    @Json(name = "species")
    val species: String?,
    @Json(name = "gender")
    val gender: String?,
    @Json(name = "house")
    val house: String?,
    @Json(name = "dateOfBirth")
    val dateOfBirth: String?,
    @Json(name = "yearOfBirth")
    val yearOfBirth: Int?,
    @Json(name = "wizard")
    val wizard: Boolean?,
    @Json(name = "ancestry")
    val ancestry: String?,
    @Json(name = "eyeColour")
    val eyeColour: String?,
    @Json(name = "hairColour")
    val hairColour: String?,
    @Json(name = "wand")
    val wand: Wand?,
    @Json(name = "patronus")
    val patronus: String?,
    @Json(name = "hogwartsStudent")
    val hogwartsStudent: Boolean?,
    @Json(name = "hogwartsStaff")
    val hogwartsStaff: Boolean?,
    @Json(name = "actor")
    val actor: String?,
    @Json(name = "alternate_actors")
    val alternateActors: List<String?>?,
    @Json(name = "alive")
    val alive: Boolean?,
    @Json(name = "image")
    val image: String?
)