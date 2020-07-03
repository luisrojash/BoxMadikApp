package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.culqui.source;

import com.application.boxmadikv1.api.culqui.CargoResponse;
import com.application.boxmadikv1.api.culqui.IntegracionResponse;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.culqui.source.remote.CulquiRemote;

import org.json.JSONObject;

import retrofit2.Response;

public class CulquiRepository implements CulquiDataSource {

    private CulquiRemote remote;
    private static CulquiRepository mInstance = null;

    public static final CulquiRepository getmInstance(CulquiRemote remote) {
        if (mInstance == null) {
            mInstance = new CulquiRepository(remote);
        }
        return mInstance;
    }

    public CulquiRepository(CulquiRemote remote) {
        this.remote = remote;
    }

    @Override
    public void onCrearTokenUsuario(JSONObject jsonBody, CallBackResultado<IntegracionResponse,Integer> backResultado) {
        remote.onCrearTokenUsuario(jsonBody, backResultado);
    }

    @Override
    public void onCrearPago(JSONObject jsonBody, CallBackResultado<Response<CargoResponse>,Integer> backResultado) {
        remote.onCrearPago(jsonBody, backResultado);
    }
}
