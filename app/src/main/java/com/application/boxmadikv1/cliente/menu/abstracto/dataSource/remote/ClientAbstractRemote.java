package com.application.boxmadikv1.cliente.menu.abstracto.dataSource.remote;

import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.cliente.ListaPropuestaPendienteResponse;
import com.application.boxmadikv1.cliente.menu.abstracto.dataSource.ClientAbstractDataSource;
import com.application.boxmadikv1.utils.Constantes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientAbstractRemote implements ClientAbstractDataSource {

    public static final String TAG = ClientAbstractRemote.class.getSimpleName();

    @Override
    public void mostrarListaClienteEstados(String keyUser, String codigoPais, String tipoEstado, final CallBackResultado<ListaPropuestaPendienteResponse> listCallBackResultado) {
        Api service = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        Log.d(TAG, "codigoPais : " + codigoPais);
        service.obtenerListaPropuestaPendienteCliente(codigoPais,
                tipoEstado,
                keyUser).enqueue(new Callback<ListaPropuestaPendienteResponse>() {
            @Override
            public void onResponse(Call<ListaPropuestaPendienteResponse> call, Response<ListaPropuestaPendienteResponse> response) {
                ListaPropuestaPendienteResponse body = response.body();
                Log.d(TAG, "body : " + body.getMessage());
                listCallBackResultado.onCallBackResultado(body);
            }

            @Override
            public void onFailure(Call<ListaPropuestaPendienteResponse> call, Throwable t) {
                listCallBackResultado.onCallBackResultado(null);
              //  Log.d(TAG, "onFailure : " + t.getMessage().toString());
            }
        });
    }
}
