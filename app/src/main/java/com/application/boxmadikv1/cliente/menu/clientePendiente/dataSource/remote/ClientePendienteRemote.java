package com.application.boxmadikv1.cliente.menu.clientePendiente.dataSource.remote;

import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.cliente.ListaPropuestaPendienteResponse;
import com.application.boxmadikv1.cliente.menu.clientePendiente.dataSource.ClientePendienteDataSource;
import com.application.boxmadikv1.cliente.menu.clientePendiente.entidad.ClientePendienteUi;
import com.application.boxmadikv1.utils.Constantes;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientePendienteRemote implements ClientePendienteDataSource {

    public static final String TAG = ClientePendienteRemote.class.getSimpleName();

    @Override
    public void mostrarListaClienteTodos(String keyUser, final CallBackResultado<ListaPropuestaPendienteResponse> listCallBackResultado) {

        Api service = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        service.obtenerListaPropuestaPendienteCliente(Constantes.PAIS_CODIGO_PERU,
                Constantes.PROPUESTA_ESTADO_CLIENTE_PENDIENTE,
                keyUser).enqueue(new Callback<ListaPropuestaPendienteResponse>() {
            @Override
            public void onResponse(Call<ListaPropuestaPendienteResponse> call, Response<ListaPropuestaPendienteResponse> response) {
                ListaPropuestaPendienteResponse body = response.body();
                Log.d(TAG, "body : " + body.getMessage());
                /*for (ClientePendienteUi clientePendienteUi :
                        body.getPropuestasClientePendientes()) {
                    Log.d(TAG, "clientePendienteUi : " + clientePendienteUi.getPri_Titulo());
                }*/
                listCallBackResultado.onCallBackResultado(body);
            }

            @Override
            public void onFailure(Call<ListaPropuestaPendienteResponse> call, Throwable t) {
                listCallBackResultado.onCallBackResultado(null);
                Log.d(TAG, "onFailure : " + t.getMessage().toString());
            }
        });


    }
}
