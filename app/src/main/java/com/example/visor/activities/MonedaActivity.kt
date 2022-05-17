package com.example.visor.activities

import com.example.visor.R
import kotlinx.android.synthetic.main.activity_moneda.*

class MonedaActivity : BaseActivity(R.layout.activity_moneda){

    override fun configurarVista() {
        label.text="Hola desde MonedaActivty"
    }
}