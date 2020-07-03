package com.application.boxmadikv1.splash.dataSource.remote;

import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.DatosInicialResponse;
import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.splash.dataSource.SplashDataSource;
import com.application.boxmadikv1.utils.Constantes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashRemote implements SplashDataSource {

    public static final String TAG = SplashRemote.class.getSimpleName();

    @Override
    public void onImportartDatosIniciales(final CallBackResultado<DatosInicialResponse, String> booleanCallBackResultado) {
/*        String codePeru = "51";
        String baseUrl = "http://192.168.1.14/Archivosphp/api/";
        String baseRemote = "http://diazosorio.com/BoxMyApi/api/";*/
        Api service = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);

        service.obtenerDatosGeneralesImportAndroid()
                .enqueue(new Callback<DatosInicialResponse>() {
                    @Override
                    public void onResponse(Call<DatosInicialResponse> call, Response<DatosInicialResponse> response) {
                        DatosInicialResponse datosInicialResponse = response.body();
                        booleanCallBackResultado.onCallBackResultado(datosInicialResponse, "");
                    }

                    @Override
                    public void onFailure(Call<DatosInicialResponse> call, Throwable t) {
                        booleanCallBackResultado.onCallBackResultado(null, "");
                    }
                });
    }
}
