package com.application.boxmadikv1.registraUser.terminos;

import android.content.Intent;

import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;
import com.application.boxmadikv1.terminosCondiciones.TerminosCondicionesActivity;

public class RegistroTerminosActivity extends TerminosCondicionesActivity {

    @Override
    public void initStartActivity() {

    }

    @Override
    public void initStartActivityReportePago(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi) {

    }

    @Override
    public void initStartActivityRegistroUser(boolean aceptarTermino) {
        Intent intent = new Intent();
        intent.putExtra("keyName", aceptarTermino);
        setResult(RESULT_OK, intent);
        finish();
    }
}
