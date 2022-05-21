package com.example.visor.utils

import android.content.res.Resources
import com.example.visor.R

fun Boolean.siONo(resources: Resources) : String {
    return resources.getString(
        if (this) R.string.si else R.string.no
    )
}