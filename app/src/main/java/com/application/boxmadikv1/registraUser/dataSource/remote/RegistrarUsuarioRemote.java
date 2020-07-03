package com.application.boxmadikv1.registraUser.dataSource.remote;

import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.registraUser.dataSource.RegistrarUsuarioDataSource;
import com.application.boxmadikv1.registraUser.entidad.TipoDocumentoUi;
import com.application.boxmadikv1.registraUser.entidad.TipoPaisUi;
import com.application.boxmadikv1.utils.Constantes;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrarUsuarioRemote implements RegistrarUsuarioDataSource {

    public static final String TAG = RegistrarUsuarioRemote.class.getSimpleName();


    @Override
    public void onRegistrarUsuario(RequestBody requestFile, RequestBody requestBodyTipoDoc, RequestBody requestBodyNombre, RequestBody requestBodyApellidos, RequestBody requestBodyEmail, RequestBody requestBodyClave, RequestBody requestBodyCelular, RequestBody requestBodyTipoDocumentoId, RequestBody requestBodyTipoPaisId, RequestBody requestBodyDateTimeCumple,RequestBody requestBodyRazonSocial, final CallbackResultado<DefaultResponse> defaultResponseCallbackResultado) {
        String baseUrl = "http://192.168.1.14/Archivosphp/api/";
        String baseRemote = "http://diazosorio.com/BoxMyApi/api/";
        Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        Call<DefaultResponse> call = loginService.crearUsuarioImage(requestFile,
                requestBodyTipoDoc,
                requestBodyNombre,
                requestBodyApellidos,
                requestBodyEmail,
                requestBodyClave,
                requestBodyCelular,
                requestBodyTipoDocumentoId,
                requestBodyTipoPaisId,
                requestBodyDateTimeCumple,
                requestBodyRazonSocial);
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        DefaultResponse message = response.body();
                        defaultResponseCallbackResultado.onCallBackResultado(message);
                    } else {
                        defaultResponseCallbackResultado.onCallBackResultado(null);
                    }
                }

            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                defaultResponseCallbackResultado.onCallBackResultado(null);
                //Log.d(TAG, "onFailure : " + t.getMessage().toString());
            }
        });
    }


    @Override
    public void onListartTipoDocumento(String tipoPaisId,CallbackResultado<List<TipoDocumentoUi>> listCallbackResultado) {

    }

    @Override
    public void onListartTipoPais(CallbackResultado<List<TipoPaisUi>> listCallbackResultado) {

    }


}
