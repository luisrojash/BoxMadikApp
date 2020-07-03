package com.application.boxmadikv1.especialista.menu.abstracto.dataSource.remote;

import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.especialista.ListaCotizacionesResponse;
import com.application.boxmadikv1.especialista.menu.abstracto.dataSource.EspecAbstractDataSource;
import com.application.boxmadikv1.especialista.menu.abstracto.entidad.EspecialistaEstadosUi;
import com.application.boxmadikv1.utils.Constantes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EspecAbstractRemote implements EspecAbstractDataSource {


    public static final String TAG = EspecAbstractRemote.class.getSimpleName();

    @Override
    public void mostrarListaClienteEnviados(String usuarioCodigo, String paisCodigo, String priEstado, final CallBackResultado<ListaCotizacionesResponse> listaCotizacionesResponseCallBackResultado) {
        Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        Call<ListaCotizacionesResponse> call = loginService.obtenerListaCotizaciones(usuarioCodigo,
                paisCodigo,
                priEstado);
        call.enqueue(new Callback<ListaCotizacionesResponse>() {
            @Override
            public void onResponse(Call<ListaCotizacionesResponse> call, Response<ListaCotizacionesResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        ListaCotizacionesResponse message = response.body();
                        Log.d(TAG, "ListaCotizacionesResponse : " + message.getMensaje());
                        listaCotizacionesResponseCallBackResultado.onCallBackResultado(message);
                    } else {
                        listaCotizacionesResponseCallBackResultado.onCallBackResultado(null);
                    }
                }

            }

            @Override
            public void onFailure(Call<ListaCotizacionesResponse> call, Throwable t) {
                listaCotizacionesResponseCallBackResultado.onCallBackResultado(null);
                Log.d(TAG, "onFailure : " );
            }
        });
    }

    @Override
    public void eliminarItem(EspecialistaEstadosUi especialistaEstadosUi, String keyUser,final CallBackResultado<DefaultResponse> defaultResponseCallBackResultado) {
        Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        Call<DefaultResponse> call = loginService.eliminarCotizacionesEspecialista(especialistaEstadosUi.getPaisCodigo(),
                keyUser,
                especialistaEstadosUi.getIdCodigoCotizacion(),
                especialistaEstadosUi.getIdCodigoPropuesta(),
                Constantes.ESTADO_ESPECIALISTA_CANCELADOS);



        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        DefaultResponse message = response.body();
                        //        Log.d(TAG, "ListaCotizacionesResponse : " + message.getMensaje());
                        defaultResponseCallBackResultado.onCallBackResultado(message);
                    } else {
                        defaultResponseCallBackResultado.onCallBackResultado(null);
                    }
                }

            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                defaultResponseCallBackResultado.onCallBackResultado(null);
                Log.d(TAG, "onFailure : " + t.getMessage().toString());
            }
        });
    }
}
