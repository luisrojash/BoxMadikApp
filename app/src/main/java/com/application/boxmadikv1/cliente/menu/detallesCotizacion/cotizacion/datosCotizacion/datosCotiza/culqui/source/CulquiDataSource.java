package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.culqui.source;

import com.application.boxmadikv1.api.culqui.CargoResponse;
import com.application.boxmadikv1.api.culqui.IntegracionResponse;

import org.json.JSONObject;

import retrofit2.Response;

public interface CulquiDataSource {

    interface CallBackResultado<T,Q> {
        void onCallBakResultado(T resultado,Q resultadoResponse);
    }


    void onCrearTokenUsuario(JSONObject jsonBody, CulquiDataSource.CallBackResultado<IntegracionResponse,Integer> backResultado);

    void onCrearPago(JSONObject jsonBody, CulquiDataSource.CallBackResultado<Response<CargoResponse>,Integer> backResultado);
}
