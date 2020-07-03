package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosPerfilEspecialista.calificacion;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.activity.BaseActivityPresenterImpl;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.DetallesCotizaFragment;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;
import com.application.boxmadikv1.utils.Constantes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalificacionPresenterImpl extends BaseActivityPresenterImpl<CalificacionView> implements CalificacionPresenter {

    public static final String TAG = CalificacionPresenterImpl.class.getSimpleName();


    public CalificacionPresenterImpl(UseCaseHandler handler, Resources res) {
        super(handler, res);
    }

    @Override
    protected String getTag() {
        return null;
    }

    @Override
    public void onBackPressed() {

    }

    DetallesCotizacionUi detallesCotizacionUi;
    CotizacionesUi cotizacionesUi;

    @Override
    public void onStart() {
        super.onStart();
        mostrarDataInicial(detallesCotizacionUi, cotizacionesUi);
    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;
        this.detallesCotizacionUi = extras.getParcelable(Constantes.EXTRAS_CLIENTE_DETALLES_PROPUESTA);
        this.cotizacionesUi = extras.getParcelable(DetallesCotizaFragment.EXTRA_DETALLES_COTIZA_COTIZACIONESUI);

       // mostrarDataInicial(detallesCotizacionUi, cotizacionesUi);
    }

    private void mostrarDataInicial(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi) {
        if (view != null) view.mostrarDataInicial(detallesCotizacionUi, cotizacionesUi);
    }

    @Override
    public void onClickCalificar(float ratingValue, String editTextComentario) {
        float valorEmpty = (float) 0.0;
        if (ratingValue == valorEmpty) {
            Log.d(TAG, "Necesita Evaluar al Especialista");
            String mensaje = "Necesita Evaluar al Especialista";
            if (view != null) view.mostrarMensaje(mensaje);
            return;
        }
        if (editTextComentario.isEmpty() || editTextComentario == null || editTextComentario.equals("")) {
            Log.d(TAG, "Necesita colocar un Comentario ");
            String mensaje = "Necesita colocar un Comentario";
            if (view != null) view.mostrarMensaje(mensaje);
            return;
        }
        initUseCaseGuardarUsuarioCalificacion(ratingValue, editTextComentario);
        Log.d(TAG, "onClickFinalizarDesemBolso : " + ratingValue +
                "editTextComentario : " + editTextComentario);
    }

    private void initUseCaseGuardarUsuarioCalificacion(float ratingValue, String editTextComentario) {
        if (view != null) view.mostrarProgressBarDialog();
        String tiporatingValue = String.valueOf(ratingValue);
        Api apiService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        Call<DefaultResponse> call = apiService.calificarCliente(detallesCotizacionUi.getPaisCodigo(),
                detallesCotizacionUi.getIdPropuesta(),
                cotizacionesUi.getIdUsuarioCotizacion(),
                detallesCotizacionUi.getUsuarioCodigoPropuesta(),
                "",
                "",
                "especialista",
                tiporatingValue,
                editTextComentario,
                detallesCotizacionUi.getPaisCodigo()
        );
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse cambioResponse = response.body();
                if (cambioResponse.getError()) {
                    if (view != null) {
                        view.mostrarMensaje(cambioResponse.getMessage());
                        view.ocultarProgressBarDialog();
                    }
                } else {
                    String mensaje = cambioResponse.getMessage();

                    initRetrofitActualizarPropuestaCoti(mensaje);
                }

            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Log.d(TAG, "cambioResponse : " + t.getMessage());
            }
        });
    }

    private void initRetrofitActualizarPropuestaCoti(final String mensaje) {

        Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);


//        Constantes.PROPUESTA_ESTADO_CLIENTE_FINALIZADO,
//                Constantes.ESTADO_ESPECIALISTA_FINALIZADO
/*
*   Constantes.PROPUESTA_ESTADO_CLIENTE_FINALIZADO,
                Constantes.ESTADO_ESPECIALISTA_PAGADO,*/
        Call<DefaultResponse> call = loginService.aceptarCotizacionClienteDeEspecialista(detallesCotizacionUi.getPaisCodigo(),
                cotizacionesUi.getIdPropuesta(),
                cotizacionesUi.getIdCotizacion(),
                detallesCotizacionUi.getUsuarioCodigoPropuesta(),
                cotizacionesUi.getIdUsuarioCotizacion(),
                Constantes.PROPUESTA_ESTADO_CLIENTE_PAGADO,
                Constantes.ESTADO_ESPECIALISTA_FINALIZADO,
                "1");
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();
                if (response != null) {
                    if (defaultResponse.getError()) {
                        if (view != null) {
                            view.mostrarMensaje(defaultResponse.getMessage());
                            view.ocultarProgressBarDialog();
                        }

                        return;
                    } else {
                        Log.d(TAG, "RegistrarCorrectamente initUseCaseCambiarEstadoPropuesta");
                        if (view != null) {
                            // view.mostrarMensaje(defaultResponse.getMessage());
                            view.ocultarProgressBarDialog();
                            /*view.initStartActivityMenu(CREADO_CORRECTAMENTE_DEMSEMBOLSO_FINALIZADO);
                            enviarNotificacionDesembolso(detallesCotizacionUi,cotizacionesUi);*/
                            view.finishActivity(mensaje);
                        }
                        return;
                    }
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                //defaultResponseCallBackResultado.onCallBackResultado(null);
                Log.d(TAG, "onFailure : " + t.getMessage().toString());
            }
        });
    }
}
