package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.dataSource;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.entidad.TipoCalidadTrabajoUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.entidad.TipoMotivoRevocacionUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.entidad.TipoSolicitaRevocanteUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;

import java.util.List;

public interface RevocacionDataSource {


    interface CallBackResultado<T> {
        void onResultado(T resultado);
    }

    void onMostrarListaTipoCalidad(RevocacionDataSource.CallBackResultado<List<TipoCalidadTrabajoUi>> listCallBackResultado);

    void onMostrarListaTipoMotivoRevocacion(RevocacionDataSource.CallBackResultado<List<TipoMotivoRevocacionUi>> listCallBackResultado);

    void onMostrarListaTipoSolicitaRevocante(RevocacionDataSource.CallBackResultado<List<TipoSolicitaRevocanteUi>> listCallBackResultado);

    void onRegistrarRevocacion(String idTipoMotivoRevocacion,
                               String idTipoCalidadTrabajo,
                               String valorPorcentajeTrabajo,
                               String idSolicitaRevocante,
                               String observacion,
                               DetallesCotizacionUi detallesCotizacionUi,
                               CotizacionesUi cotizacionesUi, RevocacionDataSource.CallBackResultado<DefaultResponse> defaultResponseCallBackResultado);

    void onCambiarEstadoPropuestaYCoti(DetallesCotizacionUi detallesCotizacionUi,
                                       CotizacionesUi cotizacionesUi, RevocacionDataSource.CallBackResultado<DefaultResponse> defaultResponseCallBackResultado);

    void onEnvioNotificacion(String grupoNotificacionCodigo, String tipoNotificacionCodigo, String usuarioCodigo, String propuestaCodigo, String paisCodigo, String codigoCotizacion,
                             RevocacionDataSource.CallBackResultado<DefaultResponse> defaultResponseCallBackResultado);


}
