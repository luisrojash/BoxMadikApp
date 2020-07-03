package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.perfilcliente.dataSource.remote;

import android.util.Log;

import com.application.boxmadikv1.api.Api;


import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.cliente.ComentarioClienteResponse;
import com.application.boxmadikv1.api.response.especialista.DatosPerfilResponse;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.perfilcliente.dataSource.PerfilClienteDataSource;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.perfilcliente.entidad.DatosCliente;
import com.application.boxmadikv1.utils.Constantes;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PerfilClienteRemote implements PerfilClienteDataSource {


    public static final String TAG = PerfilClienteRemote.class.getSimpleName();

    @Override
    public void onObtenerPerfil(String codigoUsuarioPropuesta, String estado_propuesta_proceso, String estado_propuesta_finalizada, final CallBackResultado<DatosPerfilResponse> datosPerfilResponseCallBackResultado) {
        Api apiService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);

        Call<DatosPerfilResponse> call = apiService.obtenerDatosPerfilCliente(codigoUsuarioPropuesta,
              Constantes.PAIS_CODIGO_PERU);
        call.enqueue(new Callback<DatosPerfilResponse>() {
            @Override
            public void onResponse(Call<DatosPerfilResponse> call, Response<DatosPerfilResponse> response) {
                DatosPerfilResponse cambioResponse = response.body();
                if (cambioResponse == null) return;
                datosPerfilResponseCallBackResultado.onCallBackResultado(cambioResponse);
            }

            @Override
            public void onFailure(Call<DatosPerfilResponse> call, Throwable t) {
                datosPerfilResponseCallBackResultado.onCallBackResultado(null);
                Log.d(TAG, "cambioResponse : " + t.getMessage());
            }
        });

    }

    @Override
    public void onObtenerListaComentarios(String usuCodigoPropuesta, String codigoPais, final CallBackResultado<ComentarioClienteResponse> listCallBackResultado) {
        Api apiService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        Call<ComentarioClienteResponse> call = apiService.obtenerListaComentarioCliente(usuCodigoPropuesta,
                codigoPais);
        call.enqueue(new Callback<ComentarioClienteResponse>() {
            @Override
            public void onResponse(Call<ComentarioClienteResponse> call, Response<ComentarioClienteResponse> response) {
                ComentarioClienteResponse cambioResponse = response.body();
                /*if (cambioResponse == null) return;*/
                listCallBackResultado.onCallBackResultado(cambioResponse);
            }

            @Override
            public void onFailure(Call<ComentarioClienteResponse> call, Throwable t) {
                listCallBackResultado.onCallBackResultado(null);
                Log.d(TAG, "cambioResponse : " + t.getMessage());
            }
        });
    }
}
