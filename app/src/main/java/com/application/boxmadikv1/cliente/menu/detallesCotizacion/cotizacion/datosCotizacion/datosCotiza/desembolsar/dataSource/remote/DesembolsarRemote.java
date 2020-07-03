package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.desembolsar.dataSource.remote;

import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.desembolsar.dataSource.DesembolsarDataSource;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;
import com.application.boxmadikv1.utils.Constantes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DesembolsarRemote implements DesembolsarDataSource {

    public static final String TAG = DesembolsarRemote.class.getSimpleName();

    @Override
    public void onRegistrarDesembolso(String ratingValue, String editTextComentario, DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi, final CallBackResultado<DefaultResponse> defaultResponseCallBackResultado) {

        Api apiService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        Call<DefaultResponse> call = apiService.calificarCliente(detallesCotizacionUi.getPaisCodigo(),
                detallesCotizacionUi.getIdPropuesta(),
                cotizacionesUi.getIdUsuarioCotizacion(),
                detallesCotizacionUi.getUsuarioCodigoPropuesta(),
                "",
                "",
                "especialista",
                ratingValue,
                editTextComentario,
                detallesCotizacionUi.getPaisCodigo()
        );
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        DefaultResponse message = response.body();
                        defaultResponseCallBackResultado.onCallBackResultado(message);
                    } else {
                        defaultResponseCallBackResultado.onCallBackResultado(null);
                    }
                }
                /*DefaultResponse cambioResponse = response.body();
                if (cambioResponse.getError()) {
                    if (view != null) {
                        view.mostrarMensaje(cambioResponse.getMessage());
                        view.ocultarProgressBarDialog();
                    }
                } else {
                    String mensaje = cambioResponse.getMessage();

                    initRetrofitActualizarPropuestaCoti(mensaje);
                }*/

            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                defaultResponseCallBackResultado.onCallBackResultado(null);
                Log.d(TAG, "cambioResponse : " + t.getMessage());
            }
        });


        /*Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);


        Call<DefaultResponse> call = loginService.registrarCalificacionPropuestaCliente(
                cotizacionesUi.getIdUsuarioCotizacion(),
                ratingValue,
                editTextComentario,
                detallesCotizacionUi.getIdPropuesta(),
                detallesCotizacionUi.getKeyUser());
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        DefaultResponse message = response.body();
                        defaultResponseCallBackResultado.onCallBackResultado(message);
                    } else {
                        defaultResponseCallBackResultado.onCallBackResultado(null);
                    }
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Log.d(TAG, "onFailure : " + t.getMessage().toString());
            }
        });*/




        /*Call<DefaultResponse> call = loginService.aceptarCotizacionClienteDeEspecialista(pais_codigo,
                cotizacionesUi.getIdPropuesta(),
                cotizacionesUi.getIdCotizacion(),
                detallesCotizacionUi.getUsuarioCodigoPropuesta(),
                cotizacionesUi.getIdUsuarioCotizacion(),
                Constantes.PROPUESTA_ESTADO_CLIENTE_FINALIZADO,
                Constantes.ESTADO_ESPECIALISTA_NO_ACEPTADO);
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        DefaultResponse message = response.body();
                        defaultResponseCallBackResultado.onCallBackResultado(message);
                    } else {
                        defaultResponseCallBackResultado.onCallBackResultado(null);
                    }
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                defaultResponseCallBackResultado.onCallBackResultado(null);
                Log.d(TAG, "onFailure : " + t.getMessage().toString());
            }
        });*/

    }

    @Override
    public void onCambiarEstadoFinalizado(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi, final CallBackResultado<DefaultResponse> defaultResponseCallBackResultado) {
        Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        String pais_codigo = "51";


        Call<DefaultResponse> call = loginService.aceptarCotizacionClienteDeEspecialista(pais_codigo,
                cotizacionesUi.getIdPropuesta(),
                cotizacionesUi.getIdCotizacion(),
                detallesCotizacionUi.getUsuarioCodigoPropuesta(),
                cotizacionesUi.getIdUsuarioCotizacion(),
                Constantes.PROPUESTA_ESTADO_CLIENTE_FINALIZADO,
                Constantes.ESTADO_ESPECIALISTA_PAGADO,
                "1");
                //Constantes.ESTADO_ESPECIALISTA_NO_ACEPTADO);
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        DefaultResponse message = response.body();
                        defaultResponseCallBackResultado.onCallBackResultado(message);
                    } else {
                        defaultResponseCallBackResultado.onCallBackResultado(null);
                    }
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                defaultResponseCallBackResultado.onCallBackResultado(null);
                Log.d(TAG, "onFailure : " + t.getMessage().toString());
            }
        });
    }
}
