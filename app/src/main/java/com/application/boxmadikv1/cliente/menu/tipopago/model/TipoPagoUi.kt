package com.application.boxmadikv1.cliente.menu.tipopago.model

import androidx.lifecycle.LiveData



/*data class TipoPagoUi(
        val logo: LiveData<Int>,
        val background: LiveData<Int>,
        val title: LiveData<String>,
        val montoPagar: LiveData<String>
)*/
data class TipoPagoUi(
        val logo: Int,
        val background: Int,
        val title: String,
        val montoPagar: String
)
