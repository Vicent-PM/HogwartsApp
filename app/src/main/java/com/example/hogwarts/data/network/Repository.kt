package com.example.hogwarts.data.network

import android.content.Context
import com.example.hogwarts.data.models.getCharacters.Characters

class Repository(val context: Context) {

    private val retrofit = RetrofitHelper.getRetrofit()
    suspend fun getCharacters() = retrofit.getCharacters()
    suspend fun getSpells() = retrofit.getSpells()
    suspend fun getHouseCharacters(house: String) = retrofit.getHouseCharacter(house)
    suspend fun getStaffCharacters() = retrofit.getStaffCharacters()
    suspend fun getStudentsCharacters() = retrofit.getStudentsCharacters()
}