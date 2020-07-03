package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.dataSource.remote;

import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.dataSource.DatosCotizaDataSource;
import com.application.boxmadikv1.utils.Constantes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DatosCotizaRemote implements DatosCotizaDataSource {

    public static final String TAG = DatosCotizaRemote.class.getSimpleName();

    @Override
    public void onValidarRevocacion(String pais_codigo, String propuesta_codigo, String codigo_usuario_crea, String estado_propuesta, final CallBackResultado<DefaultResponse> defaultResponseCallBackResultado) {
        Api apiService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);


        Call<DefaultResponse> call = apiService.validarRevocacion(pais_codigo,
                propuesta_codigo,
                codigo_usuario_crea,
                estado_propuesta);
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse cambioResponse = response.body();
                if (cambioResponse == null) return;
                defaultResponseCallBackResultado.onResultado(cambioResponse);
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                defaultResponseCallBackResultado.onResultado(null);
                Log.d(TAG, "cambioResponse : " + t.getMessage());
            }
        });
    }
}
