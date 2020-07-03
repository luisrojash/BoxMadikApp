package com.application.boxmadikv1.seleccionUsuario.dataSource.remote;

import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.seleccionUsuario.dataSource.SeleccionUserDataSource;
import com.application.boxmadikv1.utils.Constantes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeleccionUserRemote implements SeleccionUserDataSource {
    @Override
    public void onActualizarEstadoUsuarioDesconectado(String usuCodigo, String estado, final CallBackResultado<DefaultResponse> defaultResponseCallBackResultado) {
        Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        loginService.guardarUsuariosConectados(usuCodigo, estado).enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse loginResponse = response.body();
                defaultResponseCallBackResultado.onCallBackResultado(loginResponse);
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                defaultResponseCallBackResultado.onCallBackResultado(null);
                //Log.d(TAG, "onFailure : " + t.getMessage().toString());
            }
        });
    }
}
