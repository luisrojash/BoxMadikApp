package com.application.boxmadikv1.rptRevocacion.dataSource.local;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.especialista.ObtenerRespuestaRevocaResponse;
import com.application.boxmadikv1.dao.tipoRevocacionPropuesta.TipoRevocaPropuestaDao;
import com.application.boxmadikv1.modelo.PropuestaRevocacion;
import com.application.boxmadikv1.rptRevocacion.dataSource.RespuestaRevocacionDataSource;
import com.application.boxmadikv1.rptRevocacion.entidad.PropuestaRevocacionUi;

import java.util.ArrayList;
import java.util.List;

public class RespuestaRevocacionLocal implements RespuestaRevocacionDataSource {
    private TipoRevocaPropuestaDao tipoRevocaPropuestaDao;

    public RespuestaRevocacionLocal(TipoRevocaPropuestaDao tipoRevocaPropuestaDao) {
        this.tipoRevocaPropuestaDao = tipoRevocaPropuestaDao;
    }

    @Override
    public void onMostrarListaRevocacionPropuesta(onCallBackResultado<List<PropuestaRevocacionUi>> listonCallBackResultado) {
        List<PropuestaRevocacion> revocacionUiList = tipoRevocaPropuestaDao.getAll();
        List<PropuestaRevocacionUi> revocacionUiList1 = new ArrayList<>();
        PropuestaRevocacionUi propuestaRevocacionUi = new PropuestaRevocacionUi();
        propuestaRevocacionUi.setPropuestRevocacionId("0");
        propuestaRevocacionUi.setPropuestaRevocacionDes("Seleccione ...");
        revocacionUiList1.add(propuestaRevocacionUi);
        for(PropuestaRevocacion propuestaRevocacion : revocacionUiList){
            PropuestaRevocacionUi revocacionUi = new PropuestaRevocacionUi();
            revocacionUi.setPropuestRevocacionId(String.valueOf(propuestaRevocacion.getProrev_codigo()));
            revocacionUi.setPropuestaRevocacionDes(propuestaRevocacion.getProrev_descrip());
            revocacionUiList1.add(revocacionUi);
        }
        listonCallBackResultado.onResultado(revocacionUiList1);
    }

    @Override
    public void onGuardarRegistroRevocacion(String tipoRespuesta, String propuestRevocacionId, String detalleRespuesta, String propuestaId, String codigoUsuarioPropuesta, String codigoUsuarioCotizacion, onCallBackResultado<DefaultResponse> defaultResponseonCallBackResultado) {

    }

    @Override
    public void onObtenerRespuestRevocada(String pri_codigo, String codigo_user_crea, String codigo_user_resp, String revocacion_pais, onCallBackResultado<ObtenerRespuestaRevocaResponse> onCallBackResultado) {

    }
}
