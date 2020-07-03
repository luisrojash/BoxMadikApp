package com.application.boxmadikv1.cliente.menu.tipopago.reportepago

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.application.boxmadikv1.R
import kotlinx.android.synthetic.main.activity_reporte_pago.*


class ReportePagoActivity :AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reporte_pago)
        val tipoEmpresa = intent.extras?.getString("tipoEmpresa")
        val montoPagar = intent.extras?.getString("montoPagar")

        Log.i("ReportePagoActivity: ","tipoEmpresa : "+tipoEmpresa)
        Log.i("ReportePagoActivity: ","montoPagar : "+montoPagar)
        textViewPagar.setText("S/."+montoPagar)
    }
}
