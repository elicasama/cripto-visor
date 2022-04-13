package com.example.visor.api

import com.example.visor.models.Moneda
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinGeckoApi {
    @GET("coins/markets")
    suspend fun todasLasMonedas(@Query("vs_currency") vsCurrency: String = "usd"): List<Moneda>

    companion object {
        fun new(): CoinGeckoApi =
            newApi("https://api.coingecko.com/api/v3/")
    }
}