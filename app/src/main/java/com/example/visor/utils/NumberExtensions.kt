package com.example.visor.utils

import java.text.DecimalFormat

fun Number.formateado(): String {
    return DecimalFormat().format(this)
}