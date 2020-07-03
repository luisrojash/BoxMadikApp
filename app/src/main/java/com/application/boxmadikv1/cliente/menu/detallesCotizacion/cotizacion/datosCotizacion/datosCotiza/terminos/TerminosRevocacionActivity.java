package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.terminos;

import android.content.Intent;
import android.util.Log;

import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.DetallesCotizaFragment;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.RevocacionActivity;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;
import com.application.boxmadikv1.terminosCondiciones.TerminosCondicionesActivity;
import com.application.boxmadikv1.utils.Constantes;

public class TerminosRevocacionActivity extends TerminosCondicionesActivity {
    @Override
    public void initStartActivity() {

    }

    @Override
    public void initStartActivityReportePago(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi) {
        Intent intent = new Intent(getActivity(), RevocacionActivity.class);
        intent.putExtra(Constantes.EXTRAS_CLIENTE_DETALLES_PROPUESTA, detallesCotizacionUi);
        intent.putExtra(DetallesCotizaFragment.EXTRA_DETALLES_COTIZA_COTIZACIONESUI, cotizacionesUi);
        startActivity(intent);
    }

    @Override
    public void initStartActivityRegistroUser(boolean aceptarTermino) {

    }
}
