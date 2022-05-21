package com.example.visor.activities

import android.annotation.SuppressLint
import com.example.visor.R
import com.example.visor.models.Moneda
import com.example.visor.utils.formateado
import kotlinx.android.synthetic.main.activity_moneda.*
import kotlinx.android.synthetic.main.item_moneda.*
import java.lang.RuntimeException

class MonedaActivity : BaseActivity(R.layout.activity_moneda){

    override fun configurarVista() {
        val moneda = intent.extras?.getSerializable("moneda") as? Moneda
            ?: throw RuntimeException("Necesito una moneda para funcionar")
        dibujarMoneda(contenedor_moneda, moneda)

        val detalle = resources.getString(
            R.string.detalle_moneda,
            moneda.nombre,
            moneda.variacionFormarteada(),
            moneda.precioActual.formateado(),
            moneda.ranking.toString(),
            moneda.esEstable().toString(),
            moneda.subioMuchoHoy().toString(),
            moneda.capitalizacionDeMercado.formateado()
        )

        detalle_moneda.text = detalle

    }
}