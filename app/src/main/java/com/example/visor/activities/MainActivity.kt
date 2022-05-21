package com.example.visor.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.visor.R
import com.example.visor.api.CoinGeckoApi
import com.example.visor.models.Moneda
import j3.simple_recycler_adapter.SimpleRecyclerAdapter
import j3.simple_recycler_adapter.setup
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_moneda.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivity : BaseActivity(R.layout.activity_main) {

    private val coinGeckoApi = CoinGeckoApi.new()


    override fun configurarVista() {

       val adapter = SimpleRecyclerAdapter<Moneda>(R.layout.item_moneda) { view, moneda, _ ->
            dibujarMoneda(view, moneda) {
                startActivity(
                    Intent(this, MonedaActivity::class.java)
                        .putExtra("moneda", moneda)
                )
            }
        }

        lista_monedas.setup(adapter)
        cargarMonedas(adapter)

        boton_ordenar_alfabeticamente.setOnClickListener {
            adapter.populate(
                adapter.items.sortedBy { it.nombre}

            )
            mostrarCartelito(R.string.lista_ordenada_alfabeticamente)
            boton_ordenar_alfabeticamente.isVisible = false
            boton_ordenar_market_cap.isVisible = true

        }

        boton_ordenar_market_cap.setOnClickListener {
            adapter.populate(
                adapter.items.sortedBy { it.capitalizacionDeMercado}
            )
            mostrarCartelito(R.string.lista_ordenada_market_cap)
            boton_ordenar_market_cap.isVisible = false
            boton_ordenar_alfabeticamente.isVisible = true
        }

        boton_solo_estables.setOnClickListener {
            adapter.populate(
                adapter.items.filter { it.esEstable() }
            )

            boton_solo_estables.isVisible = false
            boton_listar_todas.isVisible = true
        }

        boton_listar_todas.setOnClickListener {
            boton_listar_todas.isVisible = false
            cargarMonedas(adapter)
        }
    }

    private fun cargarMonedas(adapter: SimpleRecyclerAdapter<Moneda>) {
        progress_bar.isVisible =  true
        launch {
            try {
                val monedas = coinGeckoApi.todasLasMonedas()
                adapter.populate(monedas)
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