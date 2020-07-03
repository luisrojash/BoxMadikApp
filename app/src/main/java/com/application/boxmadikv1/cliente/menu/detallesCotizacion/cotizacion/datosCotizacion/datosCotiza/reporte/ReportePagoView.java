package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.reporte;

import com.application.boxmadikv1.base.activity.BaseActivityView;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;

public interface ReportePagoView extends BaseActivityView<ReportePagoPresenter> {

    void mostrarDataInicial(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi);

    void ocultarTeclado();

    void mostrarDialogoDeConfirmacion(String mensaje);

    void mostrarMensaje(String message);

    void initStartActivityMenuCliente(String estado);

    void initStartActivityDatosCotiza(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi);
}
