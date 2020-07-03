package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.dataSource;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.dataSource.remote.DatosCotizaRemote;

public class DatosCotizaRepository implements DatosCotizaDataSource {

    private static DatosCotizaRepository mInstance = null;
    private DatosCotizaRemote datosCotizaRemote;

    public DatosCotizaRepository(DatosCotizaRemote datosCotizaRemote) {
        this.datosCotizaRemote = datosCotizaRemote;
    }

    public static final DatosCotizaRepository getmInstance(DatosCotizaRemote datosCotizaRemote) {
        if (mInstance == null) {
            mInstance = new DatosCotizaRepository(datosCotizaRemote);
        }
        return mInstance;
    }

    @Override
    public void onValidarRevocacion(String pais_codigo, String propuesta_codigo, String codigo_usuario_crea, String estado_propuesta, CallBackResultado<DefaultResponse> defaultResponseCallBackResultado) {
        datosCotizaRemote.onValidarRevocacion(pais_codigo, propuesta_codigo, codigo_usuario_crea, estado_propuesta, defaultResponseCallBackResultado);
    }
}
