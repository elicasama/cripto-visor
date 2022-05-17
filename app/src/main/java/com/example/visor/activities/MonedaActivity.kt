package com.example.visor.activities

import com.example.visor.R
import com.example.visor.models.Moneda
import kotlinx.android.synthetic.main.activity_moneda.*
import java.lang.RuntimeException

class MonedaActivity : BaseActivity(R.layout.activity_moneda){

    override fun configurarVista() {
        val moneda = intent.extras?.getSerializable("moneda") as? Moneda
            ?: throw RuntimeException("Necesito una moneda para funcionar")

        label.text= "Acá va el detalle de la moneda ${moneda.nombre}"
    }
}