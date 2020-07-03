package com.application.boxmadikv1.recuperarClave.dataSource.remote;

import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.recuperarClave.dataSource.RecuperarClaveDataSource;
import com.application.boxmadikv1.utils.Constantes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecuperarClaveRemote implements RecuperarClaveDataSource {

    public static final String TAG = RecuperarClaveRemote.class.getSimpleName();

    @Override
    public void onRecuperarClave(String email, final CallbackResultado<Boolean, String> callbackResultado) {
        Api service = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);

        service.cambiarClaveUsuario(email)
                .enqueue(new Callback<DefaultResponse>() {
                    @Override
                    public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                        DefaultResponse defaultResponse = response.body();
                        if (defaultResponse != null) {
                            if(defaultResponse.getError()){
                                callbackResultado.onResultado(true, defaultResponse.getMessage());
                            }else{
                                callbackResultado.onResultado(false, defaultResponse.getMessage());
                            }
                        }else{
                            Log.d(TAG, "RecuperarClaveRemote::onResponse Nulo" );
                            callbackResultado.onResultado(true, "Error con nuestros Servidores");
                        }
                    }

                    @Override
                    public void onFailure(Call<DefaultResponse> call, Throwable t) {
                        Log.d(TAG, "RecuperarClaveRemote::onFailure" + t.getMessage().toString());
                        callbackResultado.onResultado(true, "Error con nuestros Servidores");
                        //  booleanCallBackResultado.onCallBackResultado(null, "");
                    }
                });
    }
}
