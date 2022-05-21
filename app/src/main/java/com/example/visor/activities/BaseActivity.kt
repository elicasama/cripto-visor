package com.example.visor.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.visor.R
import com.example.visor.api.CoinGeckoApi
import com.example.visor.models.Moneda
import com.example.visor.utils.formateado
import kotlinx.android.synthetic.main.item_moneda.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity (@LayoutRes val layout: Int) : AppCompatActivity(), CoroutineScope {
    override val coroutineContext: CoroutineContext get() = Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)

        configurarVista()
    }

    abstract fun configurarVista()

    protected fun dibujarMoneda(view: View, moneda: Moneda, onClick: () -> Unit = {}) {
        view.etiqueta_nombre.text = moneda.nombre
        view.etiqueta_precio.text = moneda.precioActual.formateado()
        Glide.with(this).load(moneda.icono).into(view.imagen_icono)
        view.etiqueta_variacion.text = moneda.variacionFormarteada()
        view.etiqueta_variacion.setTextColor(
            resources.getColor(
                if (moneda.estaEnAlza()) R.color.alza else R.color.baja
            )
        )
        view.contenedor_moneda.setOnClickListener() {
            onClick()
        }
    }
}