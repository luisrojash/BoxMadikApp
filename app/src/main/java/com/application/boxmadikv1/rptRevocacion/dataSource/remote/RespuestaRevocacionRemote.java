package com.application.boxmadikv1.rptRevocacion.dataSource.remote;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.especialista.ObtenerRespuestaRevocaResponse;
import com.application.boxmadikv1.rptRevocacion.dataSource.RespuestaRevocacionDataSource;
import com.application.boxmadikv1.rptRevocacion.entidad.PropuestaRevocacionUi;
import com.application.boxmadikv1.utils.Constantes;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RespuestaRevocacionRemote implements RespuestaRevocacionDataSource {

    @Override
    public void onMostrarListaRevocacionPropuesta(onCallBackResultado<List<PropuestaRevocacionUi>> listonCallBackResultado) {

    }

    @Override
    public void onGuardarRegistroRevocacion(String tipoRespuesta, String propuestRevocacionId, String detalleRespuesta, String propuestaId, String codigoUsuarioPropuesta, String codigoUsuarioCotizacion,final onCallBackResultado<DefaultResponse> defaultResponseonCallBackResultado) {
        Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        Call<DefaultResponse> call = loginService.respuestaCotizacion(tipoRespuesta, propuestRevocacionId, propuestaId, detalleRespuesta, codigoUsuarioPropuesta, codigoUsuarioCotizacion);
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        DefaultResponse message = response.body();
                        defaultResponseonCallBackResultado.onResultado(message);
                    } else {
                        defaultResponseonCallBackResultado.onResultado(null);
                    }
                }

            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                defaultResponseonCallBackResultado.onResultado(null);
                //Log.d(TAG, "onFailure : " + t.getMessage().toString());
            }
        });
    }

    @Override
    public void onObtenerRespuestRevocada(String pri_codigo, String codigo_user_crea, String codigo_user_resp, String revocacion_pais, final onCallBackResultado<ObtenerRespuestaRevocaResponse> onCallBackResultado) {
        Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        Call<ObtenerRespuestaRevocaResponse> call = loginService.obtenerRespuestaRevocacion(
                pri_codigo, codigo_user_crea, codigo_user_resp, revocacion_pais);
        call.enqueue(new Callback<ObtenerRespuestaRevocaResponse>() {
            @Override
            public void onResponse(Call<ObtenerRespuestaRevocaResponse> call, Response<ObtenerRespuestaRevocaResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        ObtenerRespuestaRevocaResponse message = response.body();
                        onCallBackResultado.onResultado(message);
                    } else {
                        onCallBackResultado.onResultado(null);
                    }
                }

            }

            @Override
            public void onFailure(Call<ObtenerRespuestaRevocaResponse> call, Throwable t) {
                onCallBackResultado.onResultado(null);
                //Log.d(TAG, "onFailure : " + t.getMessage().toString());
            }
        });
    }
}
