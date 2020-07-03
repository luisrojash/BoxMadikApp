package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.dataSource.remote;

import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.LoginResponse;
import com.application.boxmadikv1.api.response.especialista.MostrarImagenResponse;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.dataSource.DescripcionDataSource;
import com.application.boxmadikv1.utils.Constantes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DescripcionRemote implements DescripcionDataSource {

    public static final String TAG = DescripcionRemote.class.getSimpleName();


    @Override
    public void onMostrarImagenPropuesta(String codigo_propuesta_inicial, final CallbackResultado<MostrarImagenResponse> mostrarImagenResponseCallbackResultado) {

        String baseUrl = "http://192.168.1.14/Archivosphp/api/";
        String baseRemote = "http://diazosorio.com/BoxMyApi/api/";

        Api apiService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);

        apiService.mostrarImagenPropuesta(codigo_propuesta_inicial).enqueue(new Callback<MostrarImagenResponse>() {
            @Override
            public void onResponse(Call<MostrarImagenResponse> call, Response<MostrarImagenResponse> response) {
                MostrarImagenResponse body = response.body();
                if (body == null) return;
                mostrarImagenResponseCallbackResultado.onResultado(body);
            }

            @Override
            public void onFailure(Call<MostrarImagenResponse> call, Throwable t) {
                //mostrarImagenResponseCallbackResultado.onResultado(null);
                Log.d(TAG, "onFailure : " + t.getMessage().toString());
            }
        });
    }
}
