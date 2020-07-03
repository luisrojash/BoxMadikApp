package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.dataSource;

import com.application.boxmadikv1.api.response.cliente.ListaCotizaResponse;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.dataSource.remote.DetallesCotizaRemote;

public class DetallesCotizaRepository implements DetallesCotizaDataSource {

    private static DetallesCotizaRepository mInstance = null;

    private DetallesCotizaRemote detallesCotizaRemote;

    public static final DetallesCotizaRepository getmInstance(DetallesCotizaRemote detallesCotizaRemote) {
        if (mInstance == null) {
            mInstance = new DetallesCotizaRepository(detallesCotizaRemote);
        }
        return mInstance;
    }

    public DetallesCotizaRepository(DetallesCotizaRemote detallesCotizaRemote) {
        this.detallesCotizaRemote = detallesCotizaRemote;
    }

    @Override
    public void onListarCotizaciones(String usu_codigo, String pais_codigo, String propuesta_inicial_codigo, String estadoCotiza, CallBackResultado<ListaCotizaResponse> listaCotizaResponseCallBackResultado) {
        detallesCotizaRemote.onListarCotizaciones(usu_codigo, pais_codigo, propuesta_inicial_codigo,estadoCotiza, listaCotizaResponseCallBackResultado);
    }
}
