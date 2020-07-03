package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza;


import com.application.boxmadikv1.base.activity.BaseActivityView;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;

public interface DatosCotizaView extends BaseActivityView<DatosCotizaPresenter> {
    void mostrarDataInicial(String imagen, String nombreEspecialista, float puntuacion, String detallesCotiza,
                            String cotiPendiente, String cotiFinalizado, String cotiAceptado, String monto, String imagenPais, String fechaCotiza);

    void initStartActivityTerminoCondicionesPagar(DetallesCotizacionUi detallesCotizacionUi,
                                                  CotizacionesUi cotizacionesUi);

    void mostrarButtonAceptar();

    void ocultarButtonDesembolsar();

    void ocultarButtonAceptar();

    void mostrarButtonDesembolsar();

    void mostrarMensaje(String mensaje);

    void initStartActivityTerminoCondicionesRevocacion(DetallesCotizacionUi detallesCotizacionUi,
                                                       CotizacionesUi cotizacionesUi);

    void initStartActivityDesembolsar(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi);

    void mostrarButtonLevantarRevocacion();

    void mostrarButtonRevocacion();

    void ocultarTeclado();

    /*Boton Visibles*/

    void mostrarButtonRevocacionVisible();

    void ocultarButtonRevocacionGone();

    void starActivityChat(String tipoEstadoGrupo, String idUsuarioCotizacions, String keyUser, String idPropuestas, String idGrupoChat, String tipoUsuario, String imagenRubro, String nombrePropuesta);

    /* Boton Aceptar*/
    void deshabilitarButtonAceptarCotiza();

    void habilitarButtonAceptarCotiza();

    /* Boton Enviar Mensaje*/

    void deshabilitarButtonEnviarMensaje();

    void habilitarButtonEnviarMensaje();

    /*Boton Revocacion*/

    void deshabilitarButtonRevocacion();

    void habilitarButtonRevocacion();

    /*Boton Desembolsar*/

    void deshabilitarButtonDesembolsar();

    void habilitarButtonDesembolsar();
    /*Boton Reporte PAgo*/

    void mostrarBotonReportePago();

    void ocultarBotonReportePago();

    void deshabilitarButtonReportePago();

    void habilitarButtonReportePago();
    /*Button Levantar Revocacion*/

    void habilidatButtonLevantarRevocacion();

    void deshabilitarButtonLevantarRevocacion();


    void mostrarButtonVerRepuestaRevocada();

    void ocultarButtonVerRepuestaRevocada();

    void habilitarButtonVerRepuestaRevocada();

    void deshabilitarButtonVerRepuestaRevocada();

    void initStartActivityReportePago(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi);

    void initStartActivityReportePagoPagado(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi);

    void initStartActivityRespuestaRevocada(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi);

    void mostrarDialogConfirmacion(String mensaje, DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi);

    void mostrarProgressDialog(String mensaje);

    void ocultarProgressDialog();

    void initStartActivityMenuCliente();

    void mostraButtonLevantarRevocacion();

    void ocultarButtoLevantarRevocacion();

    void initStartActivityTipoPago(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi);
}
