package com.application.boxmadikv1.notificaciones;

import com.application.boxmadikv1.base.activity.BaseActivityView;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.ItemUi;
import com.application.boxmadikv1.notificaciones.entidad.NotificacionesUi;

import java.util.List;

public interface NotificacionesView extends BaseActivityView<NotificacionesPresenter>{
    void mostrarLista(List<NotificacionesUi> notificacionesUiList);

    void mostrarListaAdd(List<NotificacionesUi> notificacionesUiList);

    void initStartActivityDetallesPropuesta(ItemUi itemUi);

    void initStartActivityDetallesCotizacionUser(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi);

    void limpiarVista(List<NotificacionesUi> notificacionesUiList);

    void actualizarVistaNotificacion(NotificacionesUi notificacionesUi);

    void mostrarTextViewEmpty();

    void ocultarTextViewEmpty();

    void mostrarProgressDialog();

    void ocultarProgressDialog();

    void mostrarMensaje(String mensaje);

    void initStartActivityRespuestaRevocacion(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi, String tipousuario);

    void initStartActivityPerfilPropuesta(ItemUi itemUi, int tipoDataEnviar);
}
