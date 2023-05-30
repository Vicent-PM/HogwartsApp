package com.example.hogwarts.data.network

import android.content.Context

class Repository(val context: Context) {

    private val retrofit = RetrofitHelper.getRetrofit()
    suspend fun getCharacters() = retrofit.getCharacters()
}