package com.application.boxmadikv1.especialista.menu.buscarPropuesta.dataSource.remote;

import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.especialista.ListaPropuestaTotalResponse;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.dataSource.BuscarPropuestaDataSource;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.entidad.MisRubrosUi;
import com.application.boxmadikv1.modelo.PropuestaInicial;
import com.application.boxmadikv1.utils.Constantes;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuscarPropuestaRemote implements BuscarPropuestaDataSource {
    public static final String TAG = BuscarPropuestaRemote.class.getSimpleName();

    @Override
    public void onMostrarListaRubros(ArrayList<String> stringListIdRubros, String userKey, CallbackResultado<List<MisRubrosUi>> listCallbackResultado) {

    }

    @Override
    public void onMostrarListaTotalRubros(int primerRubroId, int segundoRubroId,
                                          int tercerRubrodId, int estado, String userKey,
                                          String depaCodigo,
                                          int pageCount,String codigoPais,final  CallbackResultado<ListaPropuestaTotalResponse> listaPropuestaTotalResponseCallbackResultado) {
        Api service = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);

        Log.d(TAG,"depaCodigo "+depaCodigo+
                "primerRubroId " + primerRubroId
                + " / segundo " + segundoRubroId
                + "/ tercero " + tercerRubrodId);

        service.obtenerListaPropuestaTotalEspecialista(primerRubroId,
                segundoRubroId,
                tercerRubrodId,
                estado,
                userKey,
                depaCodigo,codigoPais).enqueue(new Callback<ListaPropuestaTotalResponse>() {
            @Override
            public void onResponse(Call<ListaPropuestaTotalResponse> call, Response<ListaPropuestaTotalResponse> response) {
                ListaPropuestaTotalResponse body = response.body();
                listaPropuestaTotalResponseCallbackResultado.onCallBackResultado(body);
            }

            @Override
            public void onFailure(Call<ListaPropuestaTotalResponse> call, Throwable t) {
                //listCallBackResultado.onCallBackResultado(null);
                Log.d(TAG, "onFailure : " + t.getMessage().toString());
                listaPropuestaTotalResponseCallbackResultado.onCallBackResultado(null);
            }
        });
       /* service.loadMorePropuesta(primerRubroId,
                segundoRubroId,
                tercerRubrodId,
                estado,
                userKey,
                depaCodigo,pageCount).enqueue(new Callback<ListaPropuestaTotalResponse>() {
            @Override
            public void onResponse(Call<ListaPropuestaTotalResponse> call, Response<ListaPropuestaTotalResponse> response) {
                ListaPropuestaTotalResponse body = response.body();
                listaPropuestaTotalResponseCallbackResultado.onCallBackResultado(body);
            }

            @Override
            public void onFailure(Call<ListaPropuestaTotalResponse> call, Throwable t) {
                //listCallBackResultado.onCallBackResultado(null);
                Log.d(TAG, "onFailure : " + t.getMessage().toString());
                listaPropuestaTotalResponseCallbackResultado.onCallBackResultado(null);
            }
        });*/
    }

    /*@Override
    public void onMostrarListaRubros(ArrayList<String> stringListIdRubros, CallbackResultado<List<MisRubrosUi>> listCallbackResultado) {

    }

    @Override
    public void onMostrarListaTotalRubros(int primerRubroId, int segundoRubroId, int tercerRubrodId, int estado, final CallbackResultado<ListaPropuestaTotalResponse> listaPropuestaTotalResponseCallbackResultado) {

        Api service = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);

        Log.d(TAG, "primerRubroId " + primerRubroId
                + " / segundo " + segundoRubroId
                + "/ tercero " + tercerRubrodId);

        service.obtenerListaPropuestaTotalEspecialista(primerRubroId,
                segundoRubroId,
                tercerRubrodId,
                estado).enqueue(new Callback<ListaPropuestaTotalResponse>() {
            @Override
            public void onResponse(Call<ListaPropuestaTotalResponse> call, Response<ListaPropuestaTotalResponse> response) {
                ListaPropuestaTotalResponse body = response.body();
                listaPropuestaTotalResponseCallbackResultado.onCallBackResultado(body);
            }

            @Override
            public void onFailure(Call<ListaPropuestaTotalResponse> call, Throwable t) {
                //listCallBackResultado.onCallBackResultado(null);
                Log.d(TAG, "onFailure : " + t.getMessage().toString());
                listaPropuestaTotalResponseCallbackResultado.onCallBackResultado(null);
            }
        });
    }*/

}
