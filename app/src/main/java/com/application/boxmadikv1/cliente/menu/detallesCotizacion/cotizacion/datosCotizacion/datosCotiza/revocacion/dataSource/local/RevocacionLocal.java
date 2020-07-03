package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.dataSource.local;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.dataSource.RevocacionDataSource;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.entidad.TipoCalidadTrabajoUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.entidad.TipoMotivoRevocacionUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.entidad.TipoSolicitaRevocanteUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;
import com.application.boxmadikv1.dao.calidadTrabajo.CalidadTrabajoDao;
import com.application.boxmadikv1.dao.motivoRevocacion.MotivoRevocacionDao;
import com.application.boxmadikv1.dao.solucionRevocacion.SolucionRevocacionDao;
import com.application.boxmadikv1.modelo.CalidadTrabajo;
import com.application.boxmadikv1.modelo.MotivoRevocacion;
import com.application.boxmadikv1.modelo.SolucionRevocacion;
import com.application.boxmadikv1.utils.Constantes;

import java.util.ArrayList;
import java.util.List;

public class RevocacionLocal implements RevocacionDataSource {

    private MotivoRevocacionDao motivoRevocacionDao;
    private SolucionRevocacionDao solucionRevocacionDao;
    private CalidadTrabajoDao calidadTrabajoDao;


    public RevocacionLocal(MotivoRevocacionDao motivoRevocacionDao, SolucionRevocacionDao solucionRevocacionDao, CalidadTrabajoDao calidadTrabajoDao) {
        this.motivoRevocacionDao = motivoRevocacionDao;
        this.solucionRevocacionDao = solucionRevocacionDao;
        this.calidadTrabajoDao = calidadTrabajoDao;
    }

    @Override
    public void onMostrarListaTipoCalidad(CallBackResultado<List<TipoCalidadTrabajoUi>> listCallBackResultado) {
        List<CalidadTrabajo> calidadTrabajoList = calidadTrabajoDao.obtenerListaCalidadaTrabajoActivo(Constantes.ESTADO_ACTIVO);

        List<TipoCalidadTrabajoUi> tipoMotivoRevocacionUis = new ArrayList<>();

        TipoCalidadTrabajoUi tipoCalidadTrabajoUiVacio = new TipoCalidadTrabajoUi();
        tipoCalidadTrabajoUiVacio.setId("0");
        tipoCalidadTrabajoUiVacio.setDescripcion("Seleccione ...");
        tipoMotivoRevocacionUis.add(tipoCalidadTrabajoUiVacio);
        for (CalidadTrabajo calidadTrabajo : calidadTrabajoList) {
            TipoCalidadTrabajoUi tipoCalidadTrabajoUi = new TipoCalidadTrabajoUi();
            tipoCalidadTrabajoUi.setId(String.valueOf(calidadTrabajo.getCtra_codigo()));
            tipoCalidadTrabajoUi.setDescripcion(calidadTrabajo.getCtra_descripcion());
            tipoMotivoRevocacionUis.add(tipoCalidadTrabajoUi);
        }
        listCallBackResultado.onResultado(tipoMotivoRevocacionUis);
    }

    @Override
    public void onMostrarListaTipoMotivoRevocacion(CallBackResultado<List<TipoMotivoRevocacionUi>> listCallBackResultado) {
        List<MotivoRevocacion> motivoRevocacionList = motivoRevocacionDao.obtenerListaRevocacion(Constantes.ESTADO_ACTIVO);

        List<TipoMotivoRevocacionUi> tipoMotivoRevocacionUis = new ArrayList<>();

        TipoMotivoRevocacionUi tipoMotivoRevocacionUiVacio = new TipoMotivoRevocacionUi();
        tipoMotivoRevocacionUiVacio.setId("0");
        tipoMotivoRevocacionUiVacio.setDescripcion("Seleccione ...");
        tipoMotivoRevocacionUis.add(tipoMotivoRevocacionUiVacio);
        for (MotivoRevocacion motivoRevocacion : motivoRevocacionList) {
            TipoMotivoRevocacionUi tipoMotivoRevocacionUi = new TipoMotivoRevocacionUi();
            tipoMotivoRevocacionUi.setId(String.valueOf(motivoRevocacion.getMrev_codigo()));
            tipoMotivoRevocacionUi.setDescripcion(motivoRevocacion.getMrev_descripcion());
            tipoMotivoRevocacionUis.add(tipoMotivoRevocacionUi);
        }
        listCallBackResultado.onResultado(tipoMotivoRevocacionUis);
    }

    @Override
    public void onMostrarListaTipoSolicitaRevocante(CallBackResultado<List<TipoSolicitaRevocanteUi>> listCallBackResultado) {

        List<SolucionRevocacion> solucionRevocacionList = solucionRevocacionDao.obtenerListaRevocacionActiva(Constantes.ESTADO_ACTIVO);

        List<TipoSolicitaRevocanteUi> tipoSolicitaRevocanteUis = new ArrayList<>();

        TipoSolicitaRevocanteUi tipoSolicitaRevocanteUiVacio = new TipoSolicitaRevocanteUi();
        tipoSolicitaRevocanteUiVacio.setId("0");
        tipoSolicitaRevocanteUiVacio.setDescripcion("Seleccione ...");
        tipoSolicitaRevocanteUis.add(tipoSolicitaRevocanteUiVacio);
        for (SolucionRevocacion solucionRevocacion : solucionRevocacionList) {
            TipoSolicitaRevocanteUi tipoSolicitaRevocanteUi = new TipoSolicitaRevocanteUi();
            tipoSolicitaRevocanteUi.setId(String.valueOf(solucionRevocacion.getSrev_codigo()));
            tipoSolicitaRevocanteUi.setDescripcion(solucionRevocacion.getSrev_descripcion());
            tipoSolicitaRevocanteUis.add(tipoSolicitaRevocanteUi);
        }
        listCallBackResultado.onResultado(tipoSolicitaRevocanteUis);
    }

    @Override
    public void onRegistrarRevocacion(String idTipoMotivoRevocacion, String idTipoCalidadTrabajo, String valorPorcentajeTrabajo, String idSolicitaRevocante, String observacion, DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi, CallBackResultado<DefaultResponse> defaultResponseCallBackResultado) {

    }

    @Override
    public void onCambiarEstadoPropuestaYCoti(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi, CallBackResultado<DefaultResponse> defaultResponseCallBackResultado) {

    }

    @Override
    public void onEnvioNotificacion(String grupoNotificacionCodigo, String tipoNotificacionCodigo, String usuarioCodigo, String propuestaCodigo, String paisCodigo, String codigoCotizacion, CallBackResultado<DefaultResponse> defaultResponseCallBackResultado) {

    }
}
