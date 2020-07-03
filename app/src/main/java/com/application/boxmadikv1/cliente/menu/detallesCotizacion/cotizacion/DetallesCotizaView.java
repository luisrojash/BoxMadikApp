package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion;

import com.application.boxmadikv1.base.activity.BaseActivityView;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;

import java.util.List;

public interface DetallesCotizaView extends BaseActivityView<DetallesCotizaPresenter> {
    void mostrarMensaje(String message);

    void mostrarListaCotiza(List<CotizacionesUi> cotizacionesUis);

    void enviarListaActividadPrincipal(List<CotizacionesUi> cotizacionesUis);

    void startDatosCotizacionActivity(CotizacionesUi cotizacionesUi, DetallesCotizacionUi detallesCotizacionUi);

    void mostrarDialogMensaje(String s);

    void initStartActivityCalifica(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi);
}
