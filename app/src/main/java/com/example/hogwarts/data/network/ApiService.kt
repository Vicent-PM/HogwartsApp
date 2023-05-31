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

    @GET("characters/house/{house}")
    suspend fun getHouseCharacter(@Path("house") house: String): Response<List<Characters>>
    
    @GET("characters/students")
    suspend fun getStudentsCharacters(): Response<List<Characters>>

    @GET("characters/staff")
    suspend fun getStaffCharacters(): Response<List<Characters>>
}