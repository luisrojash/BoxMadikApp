package com.application.boxmadikv1.especialista.menu.especialistaPerfil.dialog.dataSource.remote;

import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.especialista.ListaCursosResponse;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.dialog.dataSource.CursosDialogDataSource;
import com.application.boxmadikv1.utils.Constantes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CursosDialogRemote implements CursosDialogDataSource {


    public static final String TAG = CursosDialogRemote.class.getSimpleName();

    @Override
    public void onMostrarListaCursos(String keyUser, final CallBackResultado<ListaCursosResponse> listaCursosResponseCallBackResultado) {
        Api service = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        service.obtenerListaCursos(keyUser)
                .enqueue(new Callback<ListaCursosResponse>() {
                    @Override
                    public void onResponse(Call<ListaCursosResponse> call, Response<ListaCursosResponse> response) {
                        ListaCursosResponse body = response.body();
                        //Log.d(TAG, "body : " + body.getMessage());*/
                        listaCursosResponseCallBackResultado.onCallBackResultado(body);
                    }

                    @Override
                    public void onFailure(Call<ListaCursosResponse> call, Throwable t) {
                        listaCursosResponseCallBackResultado.onCallBackResultado(null);
                        Log.d(TAG, "onFailure : " + t.getMessage().toString());

                    }
                });
    }
}
