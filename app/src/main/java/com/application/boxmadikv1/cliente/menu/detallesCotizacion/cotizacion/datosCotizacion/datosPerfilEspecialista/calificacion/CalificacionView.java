package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosPerfilEspecialista.calificacion;

import com.application.boxmadikv1.base.activity.BaseActivityView;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;

public interface CalificacionView extends BaseActivityView<CalificacionPresenter> {
    void mostrarDataInicial(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi);

    void mostrarMensaje(String mensaje);

    void mostrarProgressBarDialog();

    void ocultarProgressBarDialog();

    void finishActivity(String mensaje);
}
