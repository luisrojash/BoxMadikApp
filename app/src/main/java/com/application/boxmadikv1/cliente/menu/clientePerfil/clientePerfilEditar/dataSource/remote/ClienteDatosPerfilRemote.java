package com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.dataSource.remote;

import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.MostrarDatosPerfilResponse;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.dataSource.DatosPerfilDataSource;
import com.application.boxmadikv1.utils.Constantes;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClienteDatosPerfilRemote implements DatosPerfilDataSource{

    public static final String TAG = ClienteDatosPerfilRemote.class.getSimpleName();

    @Override
    public void onObtenerNombrePais(String codigoPais, CallBackResultado<String> stringDatosPerfilDataSource) {

    }

    @Override
    public void onObtenerNombreTipoDoc(String codigotipoDoc, CallBackResultado<String> stringDatosPerfilDataSource) {

    }




    /*@Override
    public void onMostrarDatosUsuario(String id_usuario ,final CallBackResultado callBackResultado) {
        Log.d(TAG, "id_usuario"+id_usuario);
        Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_LOCAL);
       String paisCodigo = "51";
        Call<MostrarDatosPerfilResponse> call = loginService.mostrarDatosPerfilCliente(id_usuario);

        call.enqueue(new Callback<MostrarDatosPerfilResponse>() {
            @Override
            public void onResponse(Call<MostrarDatosPerfilResponse> call, Response<MostrarDatosPerfilResponse> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        MostrarDatosPerfilResponse message = response.body();
                             Log.d(TAG, "ListaCotizacionesResponse : " + message.getMessage());
                        callBackResultado.onResultado(message);
                    } else {
                        Log.d(TAG, "ListaCotizacionesResponse null: " );
                        callBackResultado.onResultado(null);
                    }
                }

            }

            @Override
            public void onFailure(Call<MostrarDatosPerfilResponse> call, Throwable t) {
                callBackResultado.onResultado(null);
                Log.d(TAG, "onFailure : " + t.getMessage().toString());
            }
        });

    }*/
}
