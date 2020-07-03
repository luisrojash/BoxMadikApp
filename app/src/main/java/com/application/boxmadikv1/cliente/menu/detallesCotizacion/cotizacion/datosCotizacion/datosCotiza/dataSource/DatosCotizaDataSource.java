package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.dataSource;

import com.application.boxmadikv1.api.response.DefaultResponse;

public interface DatosCotizaDataSource {

    interface CallBackResultado<T> {
        void onResultado(T resultado);
    }


    void onValidarRevocacion(String pais_codigo,
                             String propuesta_codigo,
                             String codigo_usuario_crea,
                             String estado_propuesta, DatosCotizaDataSource.CallBackResultado<DefaultResponse> defaultResponseCallBackResultado);
}
