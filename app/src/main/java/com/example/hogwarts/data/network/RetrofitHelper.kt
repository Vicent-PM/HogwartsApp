package com.example.hogwarts.data.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitHelper {

    var retrofitService: ApiService? = null

    fun getRetrofit(): ApiService {

        if (retrofitService == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://hp-api.onrender.com/api/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

            retrofitService = retrofit.create(ApiService::class.java)
        }

        return retrofitService!!
    }
}