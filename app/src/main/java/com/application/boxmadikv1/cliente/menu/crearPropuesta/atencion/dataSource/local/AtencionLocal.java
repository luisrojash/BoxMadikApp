package com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.dataSource.local;

import com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.dataSource.AtencionDataSource;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.entidad.TipoRangoDiasUi;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.entidad.TipoRangoPrecioUi;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.entidad.TipoRangoTurnoUi;
import com.application.boxmadikv1.dao.rangoDias.RangoDiasDao;
import com.application.boxmadikv1.dao.rangoPrecio.RangoPrecioDao;
import com.application.boxmadikv1.dao.rangoTurno.RangoTurnoDao;
import com.application.boxmadikv1.modelo.RangoDias;
import com.application.boxmadikv1.modelo.RangoPrecio;
import com.application.boxmadikv1.modelo.RangoTurno;
import com.application.boxmadikv1.utils.Constantes;

import java.util.ArrayList;
import java.util.List;

public class AtencionLocal implements AtencionDataSource {
    private RangoTurnoDao rangoTurnoDao;
    private RangoDiasDao rangoDiasDao;
    private RangoPrecioDao rangoPrecioDao;

    public AtencionLocal(RangoTurnoDao rangoTurnoDao, RangoDiasDao rangoDiasDao, RangoPrecioDao rangoPrecioDao) {
        this.rangoTurnoDao = rangoTurnoDao;
        this.rangoDiasDao = rangoDiasDao;
        this.rangoPrecioDao = rangoPrecioDao;
    }

    @Override
    public void onListartTipoPrecio(String codigoPais,CallbackResultado<List<TipoRangoPrecioUi>> listCallbackResultado) {
        //List<RangoPrecio> rangoPrecioList = rangoPrecioDao.getAll();
        //List<RangoPrecio> rangoPrecioList = rangoPrecioDao.getAll();
        List<RangoPrecio> rangoPrecioList = rangoPrecioDao.obtenerListaRangoPrecioPorPais(codigoPais, Constantes.ESTADO_ACTIVO);

        List<TipoRangoPrecioUi> tipoRangoPrecioUiList = new ArrayList<>();
        TipoRangoPrecioUi tipoRangoPrecioUiVacio = new TipoRangoPrecioUi();
        tipoRangoPrecioUiVacio.setIdRangoPrecio("0");
        tipoRangoPrecioUiVacio.setDescripcion("Indique rango de precios");
        tipoRangoPrecioUiList.add(tipoRangoPrecioUiVacio);
        for (RangoPrecio rangoPrecio : rangoPrecioList) {
            TipoRangoPrecioUi tipoRangoPrecioUi = new TipoRangoPrecioUi();
            tipoRangoPrecioUi.setIdRangoPrecio(rangoPrecio.getRan_Item() + "");
            tipoRangoPrecioUi.setDescripcion(rangoPrecio.getRan_descripcion());
            tipoRangoPrecioUiList.add(tipoRangoPrecioUi);
        }
        listCallbackResultado.onCallBackResultado(tipoRangoPrecioUiList);
    }

    @Override
    public void onListartTipoDias(CallbackResultado<List<TipoRangoDiasUi>> listCallbackResultado) {
       // List<RangoDias> rangoDiasList = rangoDiasDao.getAll();obtenerListaRangoDiasEstado
        List<RangoDias> rangoDiasList = rangoDiasDao.obtenerListaRangoDiasEstado(Constantes.ESTADO_ACTIVO);
        List<TipoRangoDiasUi> tipoRangoDiasUiList = new ArrayList<>();
        TipoRangoDiasUi tipoRangoDiasUiVacio = new TipoRangoDiasUi();
        tipoRangoDiasUiVacio.setIdRangoDias("0");
        tipoRangoDiasUiVacio.setDecripcion("¿Que dia lo atendemos?");
        tipoRangoDiasUiList.add(tipoRangoDiasUiVacio);
        for (RangoDias rangoDias : rangoDiasList) {
            TipoRangoDiasUi tipoRangoDiasUi = new TipoRangoDiasUi();
            tipoRangoDiasUi.setIdRangoDias(rangoDias.getRad_Item() + "");
            tipoRangoDiasUi.setDecripcion(rangoDias.getRad_descripcion());
            tipoRangoDiasUiList.add(tipoRangoDiasUi);
        }
        listCallbackResultado.onCallBackResultado(tipoRangoDiasUiList);
    }

    @Override
    public void onListartTipoTurno(CallbackResultado<List<TipoRangoTurnoUi>> listCallbackResultado) {
        List<RangoTurno> rangoTurnoList = rangoTurnoDao.obtenerListaRangoTurnoPorEsado(Constantes.ESTADO_ACTIVO);

        List<TipoRangoTurnoUi> tipoRangoTurnoUiList = new ArrayList<>();
        TipoRangoTurnoUi tipoRangoTurnoUiVacio = new TipoRangoTurnoUi();
        tipoRangoTurnoUiVacio.setIdRangoTurno("0");
        tipoRangoTurnoUiVacio.setDescripcion("¿En que turno lo atendemos?");
        tipoRangoTurnoUiList.add(tipoRangoTurnoUiVacio);
        for (RangoTurno rangoTurno : rangoTurnoList) {
            TipoRangoTurnoUi tipoRangoTurnoUi = new TipoRangoTurnoUi();
            tipoRangoTurnoUi.setIdRangoTurno(rangoTurno.getRat_Item() + "");
            tipoRangoTurnoUi.setDescripcion(rangoTurno.getRat_descripcion());
            tipoRangoTurnoUiList.add(tipoRangoTurnoUi);
        }
        listCallbackResultado.onCallBackResultado(tipoRangoTurnoUiList);
    }
}
