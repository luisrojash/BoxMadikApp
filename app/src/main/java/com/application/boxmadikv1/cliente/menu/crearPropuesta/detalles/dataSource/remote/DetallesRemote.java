package com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.dataSource.remote;

import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.DefaultResponseRegistro;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.dataSource.DetallesDataSource;
import com.application.boxmadikv1.utils.Constantes;

import java.util.ArrayList;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetallesRemote implements DetallesDataSource {
    public static final String TAG = DetallesRemote.class.getSimpleName();


    @Override
    public void onRegistrarPropuesta(RequestBody bodyTitulo, RequestBody bodyDetalles, RequestBody bodyPaisCodigo, RequestBody bodyRangPrecioId, RequestBody bodyRangDiasId, RequestBody bodyRangTurnoId, RequestBody bodyRubroId, RequestBody bodyOficioId, ArrayList<String> listaIdEspecialistas, RequestBody requestFile1,
                                     RequestBody requestFile2, RequestBody requesKeyUser, RequestBody bodyCodigoDepartamento,
                                     RequestBody bodyCodigoProvincia,
                                     RequestBody bodyCodigoDistrito, final CallbackResultado<DefaultResponseRegistro> defaultResponseCallbackResultado) {
        Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        String convertCodigoDistrito = Constantes.requestBodyToString(bodyCodigoDistrito);
        Log.d(TAG, "convertCodigoDistrito : " + convertCodigoDistrito);
        Call<DefaultResponseRegistro> call = loginService.crearPropuestaCliente(requestFile1,
                requestFile2,
                bodyTitulo,
                bodyDetalles,
                bodyPaisCodigo,
                bodyRangPrecioId,
                bodyRangDiasId,
                bodyRangTurnoId,
                bodyRubroId,
                bodyOficioId,
                requesKeyUser,
                bodyCodigoDepartamento,
                bodyCodigoProvincia,
                bodyCodigoDistrito);

        call.enqueue(new Callback<DefaultResponseRegistro>() {
            @Override
            public void onResponse(Call<DefaultResponseRegistro> call, Response<DefaultResponseRegistro> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        DefaultResponseRegistro message = response.body();
                        Log.d(TAG, "onRegistrarPropuesta : " + message.getLastid());
                        //validarEspecialidades();
                        defaultResponseCallbackResultado.onCallbackResultado(message);
                    } else {
                        defaultResponseCallbackResultado.onCallbackResultado(null);
                    }
                }
            }

            @Override
            public void onFailure(Call<DefaultResponseRegistro> call, Throwable t) {
                defaultResponseCallbackResultado.onCallbackResultado(null);
                Log.d(TAG, "onFailure : " + t.getMessage().toString());
            }
        });

    }

    @Override
    public void onRegistrarEspecialidad(String userLastId, int rubroId, int oficioId, String codigoPais, ArrayList<String> listaIdEspecialistas, CallbackResultado<DefaultResponseRegistro> defaultResponseCallbackResultado) {

    }

    @Override
    public void onObtenerDireccion(String codigoPais, String codigoDepartamento, String codigoProvincia, String codigoDistrito, CallbackResultadoDireccion callbackResultadoDireccion) {

    }


}
