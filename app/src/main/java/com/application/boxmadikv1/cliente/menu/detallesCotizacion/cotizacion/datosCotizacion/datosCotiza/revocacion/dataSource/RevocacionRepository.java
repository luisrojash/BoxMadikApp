package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.dataSource;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.dataSource.local.RevocacionLocal;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.dataSource.remote.RevocacionRemote;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.entidad.TipoCalidadTrabajoUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.entidad.TipoMotivoRevocacionUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.entidad.TipoSolicitaRevocanteUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;

import java.util.List;

public class RevocacionRepository implements RevocacionDataSource {

    private static RevocacionRepository mInstance = null;
    private RevocacionLocal revocacionLocal;
    private RevocacionRemote revocacionRemote;


    public RevocacionRepository(RevocacionLocal revocacionLocal, RevocacionRemote revocacionRemote) {
        this.revocacionRemote = revocacionRemote;
        this.revocacionLocal = revocacionLocal;
    }

    public static final RevocacionRepository getmInstance(RevocacionRemote revocacionRemote, RevocacionLocal revocacionLocal) {
        if (mInstance == null) {
            mInstance = new RevocacionRepository(revocacionLocal, revocacionRemote);
        }
        return mInstance;
    }

    @Override
    public void onMostrarListaTipoCalidad(CallBackResultado<List<TipoCalidadTrabajoUi>> listCallBackResultado) {
        revocacionLocal.onMostrarListaTipoCalidad(listCallBackResultado);
    }

    @Override
    public void onMostrarListaTipoMotivoRevocacion(CallBackResultado<List<TipoMotivoRevocacionUi>> listCallBackResultado) {
        revocacionLocal.onMostrarListaTipoMotivoRevocacion(listCallBackResultado);
    }

    @Override
    public void onMostrarListaTipoSolicitaRevocante(CallBackResultado<List<TipoSolicitaRevocanteUi>> listCallBackResultado) {
        revocacionLocal.onMostrarListaTipoSolicitaRevocante(listCallBackResultado);
    }


    @Override
    public void onRegistrarRevocacion(String idTipoMotivoRevocacion, String idTipoCalidadTrabajo, String valorPorcentajeTrabajo, String idSolicitaRevocante, String observacion, DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi, CallBackResultado<DefaultResponse> defaultResponseCallBackResultado) {
        revocacionRemote.onRegistrarRevocacion(idTipoMotivoRevocacion, idTipoCalidadTrabajo, valorPorcentajeTrabajo, idSolicitaRevocante, observacion, detallesCotizacionUi, cotizacionesUi,defaultResponseCallBackResultado);
    }

    @Override
    public void onCambiarEstadoPropuestaYCoti(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi, CallBackResultado<DefaultResponse> defaultResponseCallBackResultado) {
        revocacionRemote.onCambiarEstadoPropuestaYCoti(detallesCotizacionUi, cotizacionesUi, defaultResponseCallBackResultado);
    }

    @Override
    public void onEnvioNotificacion(String grupoNotificacionCodigo, String tipoNotificacionCodigo, String usuarioCodigo, String propuestaCodigo, String paisCodigo, String codigoCotizacion, CallBackResultado<DefaultResponse> defaultResponseCallBackResultado) {
        revocacionRemote.onEnvioNotificacion(grupoNotificacionCodigo, tipoNotificacionCodigo, usuarioCodigo, propuestaCodigo, paisCodigo, codigoCotizacion, defaultResponseCallBackResultado);
    }
}
