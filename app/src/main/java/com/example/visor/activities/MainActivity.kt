package com.example.visor.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    override val coroutineContext: CoroutineContext get() = Dispatchers.Main

    private val coinGeckoApi = CoinGeckoApi.new()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configurarVista()
    }

    private fun configurarVista() {
        // TODO: Usar coinGeckoApi para listar las monedas

        val monedas = listOf(
            Moneda(
                nombre = "Moneda 1",
                precioActual = 12f,
                icono = "https://libertex.org/sites/default/files/styles/blog_detail_hero/public/2021-02/cardano-price-prediction_main.jpg?itok=aCu5Cn_B"
            ),
            Moneda(
                nombre = "Moneda 2",
                precioActual = 22f,
                icono = "https://http2.mlstatic.com/D_NQ_NP_2X_18161-MLA20150777935_082014-F.webp"
            )
        )


        val adapter = SimpleRecyclerAdapter<Moneda>(R.layout.item_moneda) { view, moneda, _ ->
            view.etiqueta_nombre.text = moneda.nombre
            view.etiqueta_precio.text = moneda.precioActual.toString()
            Glide.with(this).load(moneda.icono).into(view.imagen_icono)
        }

        lista_monedas.setup(adapter)
        adapter.populate(monedas)

    }
}