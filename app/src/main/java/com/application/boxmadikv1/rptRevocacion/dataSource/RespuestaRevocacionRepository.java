package com.application.boxmadikv1.rptRevocacion.dataSource;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.especialista.ObtenerRespuestaRevocaResponse;
import com.application.boxmadikv1.rptRevocacion.dataSource.local.RespuestaRevocacionLocal;
import com.application.boxmadikv1.rptRevocacion.dataSource.remote.RespuestaRevocacionRemote;
import com.application.boxmadikv1.rptRevocacion.entidad.PropuestaRevocacionUi;

import java.util.List;

public class RespuestaRevocacionRepository implements RespuestaRevocacionDataSource {
    private static RespuestaRevocacionRepository mInstance = null;
    private RespuestaRevocacionLocal respuestaRevocacionLocal;
    private RespuestaRevocacionRemote respuestaRevocacionRemote;

    public RespuestaRevocacionRepository(RespuestaRevocacionLocal respuestaRevocacionLocal, RespuestaRevocacionRemote respuestaRevocacionRemote) {
        this.respuestaRevocacionLocal = respuestaRevocacionLocal;
        this.respuestaRevocacionRemote = respuestaRevocacionRemote;
    }
    public static final RespuestaRevocacionRepository getmInstance(RespuestaRevocacionLocal respuestaRevocacionLocal, RespuestaRevocacionRemote respuestaRevocacionRemote) {
        if(mInstance== null){
            mInstance= new RespuestaRevocacionRepository(respuestaRevocacionLocal, respuestaRevocacionRemote);
        }
        return mInstance;
    }

    @Override
    public void onMostrarListaRevocacionPropuesta(onCallBackResultado<List<PropuestaRevocacionUi>> listonCallBackResultado) {
        respuestaRevocacionLocal.onMostrarListaRevocacionPropuesta(listonCallBackResultado);
    }

    @Override
    public void onGuardarRegistroRevocacion(String tipoRespuesta, String propuestRevocacionId, String detalleRespuesta, String propuestaId, String codigoUsuarioPropuesta, String codigoUsuarioCotizacion, onCallBackResultado<DefaultResponse> defaultResponseonCallBackResultado) {
        respuestaRevocacionRemote.onGuardarRegistroRevocacion(tipoRespuesta, propuestRevocacionId, detalleRespuesta, propuestaId, codigoUsuarioPropuesta, codigoUsuarioCotizacion, defaultResponseonCallBackResultado);
    }

    @Override
    public void onObtenerRespuestRevocada(String pri_codigo, String codigo_user_crea, String codigo_user_resp, String revocacion_pais, onCallBackResultado<ObtenerRespuestaRevocaResponse> onCallBackResultado) {
        respuestaRevocacionRemote.onObtenerRespuestRevocada(pri_codigo, codigo_user_crea, codigo_user_resp, revocacion_pais, onCallBackResultado);
    }
}
