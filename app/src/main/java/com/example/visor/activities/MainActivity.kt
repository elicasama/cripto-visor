package com.example.visor.activities

import android.content.Intent
import androidx.core.view.isVisible
import com.example.visor.R
import com.example.visor.api.CoinGeckoApi
import com.example.visor.models.Moneda
import j3.simple_recycler_adapter.SimpleRecyclerAdapter
import j3.simple_recycler_adapter.setup
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_moneda.view.*
import kotlinx.coroutines.launch

class MainActivity : BaseActivity(R.layout.activity_main) {
    enum class Orden { Alfabetico, MarketCap }
    enum class Filtro { Ninguno, Estables }

    private val coinGeckoApi = CoinGeckoApi.new()
    private var monedas: List<Moneda> = listOf()
    private var orden: Orden = Orden.MarketCap
    private var filtro: Filtro = Filtro.Ninguno

    override fun configurarVista() {
       val adapter = SimpleRecyclerAdapter<Moneda>(R.layout.item_moneda) { view, moneda, _ ->
            dibujarMoneda(view, moneda, onClick = {
                startActivity(
                    Intent(this, MonedaActivity::class.java)
                        .putExtra("moneda", moneda)
                )
            })
        }

        lista_monedas.setup(adapter)
        cargarMonedas(adapter)

        boton_ordenar_alfabeticamente.setOnClickListener {
            orden = Orden.Alfabetico
            actualizar(adapter)
            mostrarCartelito(R.string.lista_ordenada_alfabeticamente)
            boton_ordenar_alfabeticamente.isVisible = false
            boton_ordenar_market_cap.isVisible = true

        }

        boton_ordenar_market_cap.setOnClickListener {
            orden = Orden.MarketCap
            actualizar(adapter)
            mostrarCartelito(R.string.lista_ordenada_market_cap)
            boton_ordenar_market_cap.isVisible = false
            boton_ordenar_alfabeticamente.isVisible = true
        }

        boton_solo_estables.setOnClickListener {
            filtro = Filtro.Estables
            actualizar(adapter)
            boton_solo_estables.isVisible = false
            boton_listar_todas.isVisible = true
        }

        boton_listar_todas.setOnClickListener {
            filtro = Filtro.Ninguno
            actualizar(adapter)
            boton_listar_todas.isVisible = false
            boton_solo_estables.isVisible = true
            adapter.populate(monedas)
        }
    }

    private fun actualizar(adapter: SimpleRecyclerAdapter<Moneda>) {
        val listaOrdenada = when (orden) {
            Orden.Alfabetico -> monedas.sortedBy { it.nombre }
            Orden.MarketCap -> monedas.sortedBy { it.capitalizacionDeMercado }
        }
        val listaFiltrada = when (filtro) {
            Filtro.Ninguno -> listaOrdenada
            Filtro.Estables -> listaOrdenada.filter { it.esEstable() }
        }
        adapter.populate(listaFiltrada)
    }

    private fun cargarMonedas(adapter: SimpleRecyclerAdapter<Moneda>) {
        progress_bar.isVisible =  true
        launch {
            try {
                monedas = coinGeckoApi.todasLasMonedas()
                actualizar(adapter)
                boton_solo_estables.isVisible =  true
                boton_ordenar_alfabeticamente.isVisible =  true
            } catch (e: Exception) {
                mostrarCartelito(R.string.no_hay_internet)
            } finally {
                progress_bar.isVisible =  false
            }
        }

    }
}