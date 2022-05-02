package com.example.visor.models

import com.google.gson.annotations.SerializedName

data class Moneda(
    val id: String = "",

    @SerializedName("name")
    val nombre: String = "",

    @SerializedName("image")
    val icono: String = "",

    @SerializedName("current_price")
    val precioActual: Float = 0f,

    @SerializedName("price_change_percentage_24h")
    val variacionEn24hs: Double = 0.0,

    @SerializedName("market_cap_rank")
    val ranking: Int = 0
) {
    fun estaEnAlza() = variacionEn24hs >= 0
    fun variacionFormarteada() = "(${variacionPorcejanje()}% ${flechita()})"

    private fun variacionPorcejanje() = "%.2f".format(variacionEn24hs)
    private fun flechita() = if(estaEnAlza()) "▲" else "▼"
}
