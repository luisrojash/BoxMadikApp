package com.application.boxmadikv1.rptRevocacion.dataSource;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.especialista.ObtenerRespuestaRevocaResponse;
import com.application.boxmadikv1.rptRevocacion.entidad.PropuestaRevocacionUi;

import java.util.List;

public interface RespuestaRevocacionDataSource {

    interface onCallBackResultado<T> {
        void onResultado(T resltado);
    }

    void onMostrarListaRevocacionPropuesta(onCallBackResultado<List<PropuestaRevocacionUi>> listonCallBackResultado);

    void onGuardarRegistroRevocacion(String tipoRespuesta, String propuestRevocacionId, String detalleRespuesta, String propuestaId,
                                     String codigoUsuarioPropuesta, String codigoUsuarioCotizacion, onCallBackResultado<DefaultResponse> defaultResponseonCallBackResultado);

    void onObtenerRespuestRevocada(String pri_codigo, String codigo_user_crea, String codigo_user_resp,
                                   String revocacion_pais, onCallBackResultado<ObtenerRespuestaRevocaResponse> onCallBackResultado);


}
