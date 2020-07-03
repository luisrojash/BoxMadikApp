package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revoca;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.cliente.ObtenerRespuestaRevocaEspResponse;
import com.application.boxmadikv1.api.response.especialista.ObtenerRespuestaRevocaResponse;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.activity.BaseActivityPresenterImpl;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.ItemUi;
import com.application.boxmadikv1.utils.Constantes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RevocaPresenterImpl extends BaseActivityPresenterImpl<RevocaView> implements RevocaPresenter {

    public static final String TAG = RevocaPresenterImpl.class.getSimpleName();

    public RevocaPresenterImpl(UseCaseHandler handler, Resources res) {
        super(handler, res);
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onBackPressed() {

    }

    ItemUi itemUi;

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;
        itemUi = extras.getParcelable("itemUi");
    }

    @Override
    public void onStart() {
        super.onStart();
        mostrarData();
        initRetrofitData();
    }

    private void initRetrofitData() {
        Log.d(TAG,"initRetrofitData : " + itemUi.getCodigoUsuarioPropuesta()+
                "initRetrofitData : " + itemUi.getIdUsuarioCotizacion()+
                "initRetrofitData : " + itemUi.getCodigoUsuarioPropuesta());


        Log.d(TAG, "initRetrofitData" +
                " itemUi.getCodigoPropuesta()" + itemUi.getCodigoPropuesta() +
                " itemUi.getCodigoUsuarioPropuesta()" + itemUi.getCodigoUsuarioPropuesta() +
                "itemUi.getIdUsuarioCotizacion()" + itemUi.getIdUsuarioCotizacion());
        Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        Call<ObtenerRespuestaRevocaEspResponse> call = loginService.obtenerRespuestaRevocacionEspec(
                itemUi.getCodigoPropuesta(),
                itemUi.getCodigoUsuarioPropuesta(),
                itemUi.getIdUsuarioCotizacion(),
                "51");

        call.enqueue(new Callback<ObtenerRespuestaRevocaEspResponse>() {
            @Override
            public void onResponse(Call<ObtenerRespuestaRevocaEspResponse> call, Response<ObtenerRespuestaRevocaEspResponse> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        ObtenerRespuestaRevocaEspResponse obtenerRevocaEspResponse = response.body();
                        if (obtenerRevocaEspResponse != null) {
                            if (obtenerRevocaEspResponse.getError()) {
                                Log.d(TAG, "ALGUN ERROR EN EL IF algun error :else");
                            } else {
                                if (view != null) {
                                    view.mostrarDataCargada(obtenerRevocaEspResponse.getObtenerRevocaEspResponse());
                                    view.deshabilitarWidget();
                                }
                            }
                        } else {
                            Log.d(TAG, "Ocurrio algun error :else");
                        }
                    }
                } else {
                    Log.d(TAG, "Ocurrio algun error");
                }
            }

            @Override
            public void onFailure(Call<ObtenerRespuestaRevocaEspResponse> call, Throwable t) {

            }
        });

      /*  if (response == null) {
            String mensajeError = "Errores con nuestros Servidores intentelo mas Tarde";
            if (view != null) view.mostrarMensaje(mensajeError);
            return;
        }
        if (response.isSuccessful()) {
            if (response.body() != null) {

            }*/
    }

    private void mostrarData() {
        String nombrePropuesta = itemUi.getNombrePropuesta();
        String imagenRubro = itemUi.getImagePropuesta();
        if (view != null) view.mostrarDataInicial(nombrePropuesta, imagenRubro);
    }
}
