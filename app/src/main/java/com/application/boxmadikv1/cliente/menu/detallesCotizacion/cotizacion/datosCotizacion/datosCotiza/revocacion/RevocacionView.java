package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion;

import com.application.boxmadikv1.base.activity.BaseActivityView;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.entidad.TipoCalidadTrabajoUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.entidad.TipoMotivoRevocacionUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.entidad.TipoSolicitaRevocanteUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;

import java.util.List;

public interface RevocacionView extends BaseActivityView<RevocacionPresenter> {
    void mostrarListaTipoSolicitaRevocante(List<TipoSolicitaRevocanteUi> solicitaRevocanteUiList);

    void mostrarListaTipoMotivoRevocacion(List<TipoMotivoRevocacionUi> tipoMotivoRevocacionUiList);

    void mostrarListaTipoCalidadTrabajo(List<TipoCalidadTrabajoUi> tipoCalidadTrabajoUiList);

    void mostrarProcentajeTrabajo(int value);

    void mostrarMensaje(String mensaje);

    void mostrarDialogProgressBar();

    void ocultarDialogProgressBar();


    void initStarActivityDatosCotiza(String creadoCorrectamenteRevocacionCliente, DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi);

    void mostrarData(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi);

    void habilitarButtonRevocacion();

    void deshhabilitarButtonRevocacion();

    void initStartActivityMainPrincipal();
}
