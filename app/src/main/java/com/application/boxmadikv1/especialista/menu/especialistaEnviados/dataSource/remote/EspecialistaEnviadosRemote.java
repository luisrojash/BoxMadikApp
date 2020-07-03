package com.application.boxmadikv1.especialista.menu.especialistaEnviados.dataSource.remote;

import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.especialista.ListaCotizacionesResponse;
import com.application.boxmadikv1.especialista.menu.especialistaEnviados.dataSource.EspecialistaEnviadosDataSource;
import com.application.boxmadikv1.especialista.menu.especialistaEnviados.entidad.EspecialistaEnviadosUi;
import com.application.boxmadikv1.utils.Constantes;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EspecialistaEnviadosRemote implements EspecialistaEnviadosDataSource {

    public static final String TAG = EspecialistaEnviadosRemote.class.getSimpleName();

    @Override
    public void mostrarListaClienteTodos(CallBackResultado<List<EspecialistaEnviadosUi>> listCallBackResultado) {

    }

    @Override
    public void mostrarListaClienteEnviados(String usuarioCodigo, String paisCodigo, String priEstado, final CallBackResultado<ListaCotizacionesResponse> listaCotizacionesResponseCallBackResultado) {
       /* Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        Call<ListaCotizacionesResponse> call = loginService.obtenerListaCotizaciones(priEstado,
                usuarioCodigo,
                paisCodigo,
                Constantes.ESTADO_ESPECIALISTA_PENDIENTE_ENVIADOS);
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
                Log.d(TAG, "onFailure : " + t.getMessage().toString());
            }
        });*/
    }

    @Override
    public void eliminarItem(EspecialistaEnviadosUi especialistaEnviadosUi,String keyUser, final CallBackResultado<DefaultResponse> defaultResponseCallBackResultado) {
        Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        String paisCodigo = "51";
        Call<DefaultResponse> call = loginService.eliminarCotizacionesEspecialista(paisCodigo,
                keyUser,
                especialistaEnviadosUi.getIdCodigoCotizacion(),
                especialistaEnviadosUi.getIdCodigoPropuesta(),
                Constantes.ESTADO_ESPECIALISTA_REVOCADOS);

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
