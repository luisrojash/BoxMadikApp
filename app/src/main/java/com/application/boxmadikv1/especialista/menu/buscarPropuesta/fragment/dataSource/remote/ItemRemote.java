package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.dataSource.remote;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.ListaPropuestaEspecialidadResponse;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.dataSource.ItemDataSource;
import com.application.boxmadikv1.utils.Constantes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ItemRemote implements ItemDataSource {

    @Override
    public void onObtenerStringRangoPrecio(String rangoPrecioId, CallbackResultado<String> callbackResultado) {

    }

    @Override
    public void onObtenerStringRangoTurno(String rangoTurnoId, CallbackResultado<String> callbackResultado) {

    }

    @Override
    public void onObtenerStringRangoDias(String rangoDiasId, CallbackResultado<String> callbackResultado) {

    }

    @Override
    public void onObtenerRubro(String codigoRubro, TwoCallbackResultado<String,String> callbackResultado) {

    }

    @Override
    public void onObtenerOficio(String codigoOficio, CallbackResultado<String> callbackResultado) {

    }

    @Override
    public void onMostrarListaPropuestaEspecialidad(int propuestaCodigo, int rubroCodigo, int oficioCodigo, final CallbackResultado<ListaPropuestaEspecialidadResponse> listaPropuestaEspecialidadResponseCallbackResultado) {

        String baseUrl = "http://192.168.1.14/Archivosphp/api/";
        String baseRemote = "http://diazosorio.com/BoxMyApi/api/";
        /*Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        Call<ListaPropuestaEspecialidadResponse> call = loginService.obtenerListaPropuestaEspecialidad(propuestaCodigo,
                rubroCodigo,
                oficioCodigo);
        call.enqueue(new Callback<ListaPropuestaEspecialidadResponse>() {
            @Override
            public void onResponse(Call<ListaPropuestaEspecialidadResponse> call, Response<ListaPropuestaEspecialidadResponse> response) {
                ListaPropuestaEspecialidadResponse body = response.body();
                listaPropuestaEspecialidadResponseCallbackResultado.onCallBackResultado(body);
            }

            @Override
            public void onFailure(Call<ListaPropuestaEspecialidadResponse> call, Throwable t) {
                listaPropuestaEspecialidadResponseCallbackResultado.onCallBackResultado(null);
            }
        });
*/

    }
}
