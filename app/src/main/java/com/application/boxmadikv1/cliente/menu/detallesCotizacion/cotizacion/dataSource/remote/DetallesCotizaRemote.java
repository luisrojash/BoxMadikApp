package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.dataSource.remote;

import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.cliente.ListaCotizaResponse;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.dataSource.DetallesCotizaDataSource;
import com.application.boxmadikv1.utils.Constantes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetallesCotizaRemote implements DetallesCotizaDataSource {

    public static final String TAG = DetallesCotizaRemote.class.getSimpleName();

    @Override
    public void onListarCotizaciones(String usu_codigo, String pais_codigo, String propuesta_inicial_codigo, String estadoCoti, final CallBackResultado<ListaCotizaResponse> listaCotizaResponseCallBackResultado) {
        Api apiService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);

        apiService.obtenerListaCotizaCliente(usu_codigo,
                pais_codigo,
                propuesta_inicial_codigo
        ).enqueue(new Callback<ListaCotizaResponse>() {
            @Override
            public void onResponse(Call<ListaCotizaResponse> call, Response<ListaCotizaResponse> response) {
                ListaCotizaResponse body = response.body();
                if (body == null) return;
                listaCotizaResponseCallBackResultado.onCallBackResultado(body);
            }

            @Override
            public void onFailure(Call<ListaCotizaResponse> call, Throwable t) {
                Log.d(TAG, "onFailure : " + t.getMessage().toString());
                listaCotizaResponseCallBackResultado.onCallBackResultado(null);
            }
        });

    }
}
