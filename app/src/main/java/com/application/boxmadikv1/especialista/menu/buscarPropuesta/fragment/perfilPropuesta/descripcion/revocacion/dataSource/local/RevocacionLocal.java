package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion.dataSource.local;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.dao.motivoRevocacion.MotivoRevocacionDao;
import com.application.boxmadikv1.dao.solucionRevocacion.SolucionRevocacionDao;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.ItemUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion.dataSource.RevocacionDataSource;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion.entidad.TipoMotivoRevocacionUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion.entidad.TipoSolucionRevocacionUi;
import com.application.boxmadikv1.modelo.MotivoRevocacion;
import com.application.boxmadikv1.modelo.SolucionRevocacion;

import java.util.ArrayList;
import java.util.List;

public class RevocacionLocal implements RevocacionDataSource {

    private MotivoRevocacionDao motivoRevocacionDao;
    private SolucionRevocacionDao solucionRevocacionDao;

    public RevocacionLocal(MotivoRevocacionDao motivoRevocacionDao, SolucionRevocacionDao solucionRevocacionDao) {
        this.motivoRevocacionDao = motivoRevocacionDao;
        this.solucionRevocacionDao = solucionRevocacionDao;
    }

    @Override
    public void onListarTipoMotivoRevocacion(CallBackResultado<List<TipoMotivoRevocacionUi>> tipoMotivoRevocacionUiCallBackResultado) {
        List<MotivoRevocacion> motivoRevocacionList = motivoRevocacionDao.getAll();

        List<TipoMotivoRevocacionUi> tipoMotivoRevocacionUis = new ArrayList<>();

        TipoMotivoRevocacionUi tipoMotivoRevocacionUiVacio = new TipoMotivoRevocacionUi();
        tipoMotivoRevocacionUiVacio.setIdTipoMotivoRevocacion("0");
        tipoMotivoRevocacionUiVacio.setDecripcion("Seleccione Motivo Revocación");
        tipoMotivoRevocacionUis.add(tipoMotivoRevocacionUiVacio);
        for (MotivoRevocacion motivoRevocacion : motivoRevocacionList) {
            TipoMotivoRevocacionUi tipoMotivoRevocacionUi = new TipoMotivoRevocacionUi();
            tipoMotivoRevocacionUi.setIdTipoMotivoRevocacion(String.valueOf(motivoRevocacion.getMrev_codigo()));
            tipoMotivoRevocacionUi.setDecripcion(motivoRevocacion.getMrev_descripcion());
            tipoMotivoRevocacionUis.add(tipoMotivoRevocacionUi);
        }
        tipoMotivoRevocacionUiCallBackResultado.onCallBackResultado(tipoMotivoRevocacionUis);
    }

    @Override
    public void onListarTipoSolucionRevocacion(CallBackResultado<List<TipoSolucionRevocacionUi>> listCallBackResultado) {
        List<SolucionRevocacion> solucionRevocacionList = solucionRevocacionDao.getAll();

        List<TipoSolucionRevocacionUi> solucionRevocacionUiList = new ArrayList<>();

        TipoSolucionRevocacionUi tipoSolucionRevocacionUiVacia = new TipoSolucionRevocacionUi();
        tipoSolucionRevocacionUiVacia.setIdTipSolucionRevocacion("0");
        tipoSolucionRevocacionUiVacia.setDecripcion("Seleccione Solucción Revocación");
        solucionRevocacionUiList.add(tipoSolucionRevocacionUiVacia);
        for (SolucionRevocacion solucionRevocacion : solucionRevocacionList) {
            TipoSolucionRevocacionUi tipoSolucionRevocacionUi = new TipoSolucionRevocacionUi();
            tipoSolucionRevocacionUi.setIdTipSolucionRevocacion(String.valueOf(solucionRevocacion.getSrev_codigo()));
            tipoSolucionRevocacionUi.setDecripcion(solucionRevocacion.getSrev_descripcion());
            solucionRevocacionUiList.add(tipoSolucionRevocacionUi);
        }
        listCallBackResultado.onCallBackResultado(solucionRevocacionUiList);

    }

    @Override
    public void onRegistrarRevocacion(ItemUi itemUi, String idTipSolucionRevocacion, String idTipoMotivoRevocacion, int valorPorcentajeTrabajo, String observacion, CallBackResultado<DefaultResponse> defaultResponseCallBackResultado) {

    }

    @Override
    public void onEnvioNotificacion(String grupoNotificacionCodigo, String tipoNotificacionCodigo, String usuarioCodigo, String propuestaCodigo, String paisCodigo, String codigoCotizacion, CallBackResultado<DefaultResponse> defaultResponseCallBackResultado) {

    }
}
