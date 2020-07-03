package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.culqui.source.remote;

import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.culqui.CargoResponse;
import com.application.boxmadikv1.api.culqui.IntegracionResponse;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.culqui.source.CulquiDataSource;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CulquiRemote implements CulquiDataSource {
    public static final String TAG = CulquiRemote.class.getSimpleName();

    @Override
    public void onCrearTokenUsuario(JSONObject jsonBody, final CallBackResultado<IntegracionResponse,Integer> backResultado) {
        String urlCulqui = "https://api.culqi.com/v2/";
        Api loginService = RetrofitClient.createService(Api.class, urlCulqui);
        Call<IntegracionResponse> call = loginService.registerToken(jsonBody.toString());
        call.enqueue(new Callback<IntegracionResponse>() {
            @Override
            public void onResponse(Call<IntegracionResponse> call, Response<IntegracionResponse> response) {
                int dataErrorBody = response.code();
                Log.d(TAG, "dataErrorBody" + dataErrorBody);
                IntegracionResponse serverResponse = response.body();
                backResultado.onCallBakResultado(serverResponse,dataErrorBody);
            }

            @Override
            public void onFailure(Call<IntegracionResponse> call, Throwable t) {
                Log.d(TAG, "onFailure : " + t.getMessage().toString());
                backResultado.onCallBakResultado(null,null);
            }
        });
    }

    @Override
    public void onCrearPago(JSONObject jsonBody, final CallBackResultado<Response<CargoResponse>,Integer> backResultado) {
        String urlCulqui = "https://api.culqi.com/v2/";
        Api loginService = RetrofitClient.createService(Api.class, urlCulqui);
        Call<CargoResponse> call = loginService.crearCargosPago(jsonBody.toString());
        call.enqueue(new Callback<CargoResponse>() {
            @Override
            public void onResponse(Call<CargoResponse> call, Response<CargoResponse> response) {
                CargoResponse serverResponse = response.body();
                int dataErrorBody = response.code();
                if (serverResponse != null) {
                    backResultado.onCallBakResultado(response,dataErrorBody);
                }else{
                    backResultado.onCallBakResultado(response,dataErrorBody);
                }


            }

            @Override
            public void onFailure(Call<CargoResponse> call, Throwable t) {
                Log.d(TAG, "onFailure : " + t.getMessage().toString());
                backResultado.onCallBakResultado(null,null);

            }
        });
    }
}
