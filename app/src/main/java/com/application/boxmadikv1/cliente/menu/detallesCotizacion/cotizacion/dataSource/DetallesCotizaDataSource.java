package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.dataSource;

import com.application.boxmadikv1.api.response.cliente.ListaCotizaResponse;

public interface DetallesCotizaDataSource {

    interface CallBackResultado<T> {
        void onCallBackResultado(T resultado);
    }

    void onListarCotizaciones(String usu_codigo, String pais_codigo, String propuesta_inicial_codigo,String estadoCotiza,
                              CallBackResultado<ListaCotizaResponse> listaCotizaResponseCallBackResultado);


}
