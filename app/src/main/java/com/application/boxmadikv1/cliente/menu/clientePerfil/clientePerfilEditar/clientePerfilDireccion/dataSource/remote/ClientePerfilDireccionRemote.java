package com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.dataSource.remote;

import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.cliente.EditarPerfilResponse;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.dataSource.ClientePerfilDireccionDataSource;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.dataSource.DatosPerfilDataSource;
import com.application.boxmadikv1.utils.Constantes;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientePerfilDireccionRemote implements ClientePerfilDireccionDataSource {

    public static final String TAG = ClientePerfilDireccionRemote.class.getSimpleName();

    @Override
    public void onListarDepartamento(CallBackResultado callBackResultado) {

    }

    @Override
    public void onListarProvincia(String paisCodigo, String departamentoCodigo, CallBackResultadoProvincia callBackResultadoProvincia) {

    }

    @Override
    public void onListarDistrito(String paisCodigo, String departamentoCodigo, String provinciaCodigo, CallBackResultadoDistrito callBackResultadoDistrito) {

    }

    @Override
    public void onGuardarDatosEditados(RequestBody requestKeyUser, RequestBody requestBodyEstado, RequestBody requestFile, RequestBody requestBodyNombre, RequestBody requestBodyApellidos, RequestBody requestBodyCelular, RequestBody requestBodyCodigoDepartamento, RequestBody requestBodyCodigoProvincia, RequestBody requestBodyCodigoDistrito, RequestBody requestBodyLatitud, RequestBody requestBodyLongitud, RequestBody requestBodyDireccion, final DatosPerfilDataSource.CallBackResultado<EditarPerfilResponse> defaultResponseCallBackResultado) {
        Api service = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);

        /*service.guardarDatosEditadosCliente(requestFile,
                requestKeyUser, requestBodyNombre, requestBodyApellidos, requestBodyCelular, requestBodyCodigoDepartamento,
                requestBodyCodigoProvincia, requestBodyCodigoDistrito, requestBodyLatitud, requestBodyLongitud,
                requestBodyDireccion,requestBodyEstado).enqueue(new Callback<EditarPerfilResponse>() {
            @Override
            public void onResponse(Call<EditarPerfilResponse> call, Response<EditarPerfilResponse> response) {
                EditarPerfilResponse body = response.body();
                Log.d(TAG, "body : " + body.getMessage());
                defaultResponseCallBackResultado.onResultado(body);
            }

            @Override
            public void onFailure(Call<EditarPerfilResponse> call, Throwable t) {
                defaultResponseCallBackResultado.onResultado(null);
                Log.d(TAG, "onFailure : " + t.getMessage().toString());
            }
        });*/
    }
}
