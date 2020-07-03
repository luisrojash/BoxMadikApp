package com.application.boxmadikv1.login.dataSource.remote;

import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.LoginResponse;
import com.application.boxmadikv1.login.dataSource.LoginDataSource;
import com.application.boxmadikv1.utils.Constantes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRemote implements LoginDataSource {


    public static final String TAG = LoginRemote.class.getSimpleName();

    @Override
    public void onLoguear(String usuario, String clave, final CallbackResultado<LoginResponse> booleanCallbackResultado) {
        Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        loginService.loginUser(usuario, clave).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                booleanCallbackResultado.onResultado(loginResponse);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                LoginResponse loginResponse = new LoginResponse(true, "Fallo la conexión", null);
                booleanCallbackResultado.onResultado(loginResponse);
                Log.d(TAG, "onFailure : " + t.getMessage() + " / asdad "+t.getCause());
            }
        });


    }

    @Override
    public void onGuardarUsuario(LoginResponse.UsuarioResponse usuarioResponse, CallbackResultado<Boolean> booleanCallbackResultado) {

    }

    @Override
    public void onGuardarUsuariosOnline(String usuarioCodigo, String estado, String paisCodigo, final CallbackResultado<DefaultResponse> defaultResponseCallbackResultado) {
        Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        Log.d(TAG, "onGuardarUsuariosOnline : " + estado);
        loginService.guardarUsuariosConectados(usuarioCodigo, estado).enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse loginResponse = response.body();
                defaultResponseCallbackResultado.onResultado(loginResponse);
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                defaultResponseCallbackResultado.onResultado(null);
                Log.d(TAG, "onFailure : " + t.getMessage().toString());
            }
        });
    }
}
