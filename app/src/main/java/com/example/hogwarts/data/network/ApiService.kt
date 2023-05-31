package com.example.hogwarts.data.network

import com.example.hogwarts.data.models.getCharacters.Characters
import com.example.hogwarts.data.models.getSpells.Spells
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("characters")
    suspend fun getCharacters(): Response<List<Characters>>

    @GET("spells")
    suspend fun getSpells(): Response<List<Spells>>
}