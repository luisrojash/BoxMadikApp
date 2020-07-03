package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.culqui;

import com.application.boxmadikv1.base.activity.BaseActivityView;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;

public interface CulquiView extends BaseActivityView<CulquiPresenter> {
    void initStartActivityReportePago(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi);

    void mostrarMensaje(String message);

    void onPutSecurePreferences(String idToken);

    void mostrarCardViewPagoAceptado();

    void ocultarCardViewPagoAceptado();

    void mostrarDataInicial(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi);

    void mostrarDialogProgress();

    void ocultarDialogProgress();

    void deshabilitarCulquiPagoBasico();

    void habilitarCulquiPagoBasico();

    void mostraEditTextTarjetaError(String mensaje);

    void mostraEditTextAnioError(String mensaje);

    void mostraEditTextMesError(String mensaje);

    void mostraEditTextCvvError(String mensaje);

    void mostraEditTextEmailError(String mensaje);

    void deshabilitartBotonPago();

    void habilitarBotonPago();
}
