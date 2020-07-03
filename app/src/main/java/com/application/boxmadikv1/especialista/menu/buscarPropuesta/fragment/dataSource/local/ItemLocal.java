package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.dataSource.local;

import com.application.boxmadikv1.api.response.ListaPropuestaEspecialidadResponse;
import com.application.boxmadikv1.dao.oficio.OficioDao;
import com.application.boxmadikv1.dao.rangoDias.RangoDiasDao;
import com.application.boxmadikv1.dao.rangoPrecio.RangoPrecioDao;
import com.application.boxmadikv1.dao.rangoTurno.RangoTurnoDao;
import com.application.boxmadikv1.dao.rubro.RubroDao;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.dataSource.ItemDataSource;
import com.application.boxmadikv1.modelo.Oficio;
import com.application.boxmadikv1.modelo.RangoDias;
import com.application.boxmadikv1.modelo.RangoPrecio;
import com.application.boxmadikv1.modelo.RangoTurno;
import com.application.boxmadikv1.modelo.Rubro;


public class ItemLocal implements ItemDataSource {

    private RubroDao rubroDao;
    private OficioDao oficioDao;
    private RangoDiasDao rangoDiasDao;
    private RangoTurnoDao rangoTurnoDao;
    private RangoPrecioDao rangoPrecioDao;

    public ItemLocal(RubroDao rubroDao, OficioDao oficioDao, RangoDiasDao rangoDiasDao, RangoTurnoDao rangoTurnoDao, RangoPrecioDao rangoPrecioDao) {
        this.rangoDiasDao = rangoDiasDao;
        this.rangoTurnoDao = rangoTurnoDao;
        this.rangoPrecioDao = rangoPrecioDao;
        this.rubroDao = rubroDao;
        this.oficioDao = oficioDao;
    }

    @Override
    public void onObtenerStringRangoPrecio(String rangoPrecioId, CallbackResultado<String> callbackResultado) {
        RangoPrecio rangoPrecio = rangoPrecioDao.obtenerMiIdQuerySimple(Integer.parseInt(rangoPrecioId));
        callbackResultado.onCallBackResultado(rangoPrecio.getRan_descripcion());
    }

    @Override
    public void onObtenerStringRangoTurno(String rangoTurnoId, CallbackResultado<String> callbackResultado) {
        RangoTurno rangoTurno = rangoTurnoDao.obtenerMiIdQuerySimple(Integer.parseInt(rangoTurnoId));
        callbackResultado.onCallBackResultado(rangoTurno.getRat_descripcion());
    }

    @Override
    public void onObtenerStringRangoDias(String rangoDiasId, CallbackResultado<String> callbackResultado) {
        RangoDias rangoDias = rangoDiasDao.obtenerMiIdQuerySimple(Integer.parseInt(rangoDiasId));
        callbackResultado.onCallBackResultado(rangoDias.getRad_descripcion());
    }

    @Override
    public void onObtenerRubro(String codigoRubro, TwoCallbackResultado<String, String> callbackResultado) {
        Rubro rubro = rubroDao.obtenerMiIdQuerySimple(Integer.parseInt(codigoRubro));
        callbackResultado.onCallBackResultado(rubro.getRub_Imagen(), rubro.getRub_Desc());
    }

    @Override
    public void onObtenerOficio(String codigoOficio, CallbackResultado<String> callbackResultado) {
        Oficio oficio = oficioDao.obtenerMiIdQuerySimple(Integer.parseInt(codigoOficio));
        callbackResultado.onCallBackResultado(oficio.getOfi_desc());
    }

    @Override
    public void onMostrarListaPropuestaEspecialidad(int propuestaCodigo, int rubroCodigo, int oficioCodigo, CallbackResultado<ListaPropuestaEspecialidadResponse> listaPropuestaEspecialidadResponseCallbackResultado) {

    }
}
