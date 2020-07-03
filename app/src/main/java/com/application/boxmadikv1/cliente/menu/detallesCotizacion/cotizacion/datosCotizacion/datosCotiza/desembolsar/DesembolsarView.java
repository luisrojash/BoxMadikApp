package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.desembolsar;

import com.application.boxmadikv1.base.activity.BaseActivityView;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;

public interface DesembolsarView extends BaseActivityView<DesembolsarPresenter> {

    void mostrarDatainicial(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi);

    void mostrarMensaje(String mensaje);

    void ocultarDialogProgressBar();

    void mostrarDialogProgressBar();

    void initStartActivityMenu(String creadoCorrectamenteDemsembolsoFinalizado);
}
