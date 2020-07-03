package com.application.boxmadikv1.especialista.menu.especialistaPerfil.dataSource.remote;

import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.especialista.ComentarioResponse;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.dataSource.EspecialistaPerfilDataSource;
import com.application.boxmadikv1.utils.Constantes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EspecialistaPerfilRemote implements EspecialistaPerfilDataSource {

    public static final String TAG = EspecialistaPerfilRemote.class.getSimpleName();

    @Override
    public void onMostrarComentario(String keyUser, String codigoPais, final CallBackResultado<ComentarioResponse> comentarioResponseCallBackResultado) {
        Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        Call<ComentarioResponse> call = loginService.obtenerListaComentarioEspecialista(keyUser, codigoPais);


        call.enqueue(new Callback<ComentarioResponse>() {
            @Override
            public void onResponse(Call<ComentarioResponse> call, Response<ComentarioResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        ComentarioResponse message = response.body();
                        comentarioResponseCallBackResultado.onCallbackResultado(message);
                    } else {
                        comentarioResponseCallBackResultado.onCallbackResultado(null);
                    }
                }
            }

            @Override
            public void onFailure(Call<ComentarioResponse> call, Throwable t) {
                comentarioResponseCallBackResultado.onCallbackResultado(null);
                Log.d(TAG, "onFailure : " + t.getMessage().toString());
            }
        });
    }
}
