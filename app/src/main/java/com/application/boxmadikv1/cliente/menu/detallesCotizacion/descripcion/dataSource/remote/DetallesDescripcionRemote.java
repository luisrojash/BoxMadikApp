package com.application.boxmadikv1.cliente.menu.detallesCotizacion.descripcion.dataSource.remote;

import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.especialista.MostrarImagenResponse;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.descripcion.dataSource.DetallesDescripcionDataSource;
import com.application.boxmadikv1.utils.Constantes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetallesDescripcionRemote implements DetallesDescripcionDataSource {

    public static final String TAG = DetallesDescripcionRemote.class.getSimpleName();


    @Override
    public void mostrarImagenPropuesta(String codigoPropuesta, final CallBackResultado<MostrarImagenResponse> mostrarImagenResponseCallBackResultado) {
        Api apiService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);

        apiService.mostrarImagenPropuesta(codigoPropuesta).enqueue(new Callback<MostrarImagenResponse>() {
            @Override
            public void onResponse(Call<MostrarImagenResponse> call, Response<MostrarImagenResponse> response) {
                MostrarImagenResponse body = response.body();
                if (body == null) return;
                mostrarImagenResponseCallBackResultado.onCallBackResultado(body);
            }

            @Override
            public void onFailure(Call<MostrarImagenResponse> call, Throwable t) {
                //mostrarImagenResponseCallbackResultado.onResultado(null);
                Log.d(TAG, "onFailure : " + t.getMessage().toString());
            }
        });
    }
}
