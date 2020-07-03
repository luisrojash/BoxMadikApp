package com.application.boxmadikv1.terminosCondiciones;

import com.application.boxmadikv1.base.activity.BaseActivityView;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;

public interface TerminosCondicionesView extends BaseActivityView<TerminosCondicionesPresenter> {
    void mostrarCheckTrue();

    void mostrarCheckFalse();

    void mostrarMensaje(String s);

    void initStartActivity();

    void mostrarDatosTerminos(String textoTermino, String tipoEstados);

    void initStartActivityReportePago(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi);

    void initStartActivityRegistroUser(boolean aceptarTermino);

    void mostrarTituloTerminos(String tituloTerminos);
}
