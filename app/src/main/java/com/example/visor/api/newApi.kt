package com.example.visor.api

import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory

inline fun <reified T> newApi(
    baseUrl: String
): T {
    val retrofit = retrofit2.Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(T::class.java)
}