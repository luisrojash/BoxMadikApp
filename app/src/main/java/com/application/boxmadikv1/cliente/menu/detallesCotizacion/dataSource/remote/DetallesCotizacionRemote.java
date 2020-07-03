package com.application.boxmadikv1.cliente.menu.detallesCotizacion.dataSource.remote;

import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.dataSource.DetallesCotizacionDataSource;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;
import com.application.boxmadikv1.utils.Constantes;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetallesCotizacionRemote implements DetallesCotizacionDataSource {


    public static final String TAG = DetallesCotizacionRemote.class.getSimpleName();

    @Override
    public void onEliminarPropuesta(DetallesCotizacionUi detallesCotizacionU, List<CotizacionesUi> cotizacionesUiList, final CallBackResultado<DefaultResponse> defaultResponseDetallesCotizacionDataSource) {
        Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        // String pais_codigo = "51";

        /*for (CotizacionesUi cotizacionesUi : cotizacionesUiList) {
            Call<DefaultResponse> call = loginService.aceptarCotizacionClienteDeEspecialista(detallesCotizacionU.getPaisCodigo(),
                    cotizacionesUi.getIdPropuesta(),
                    cotizacionesUi.getIdCotizacion(),
                    detallesCotizacionU.getUsuarioCodigoPropuesta(),
                    cotizacionesUi.getIdUsuarioCotizacion(),
                    Constantes.PROPUESTA_ESTADO_CLIENTE_CANCELADOS,
                    Constantes.ESTADO_ESPECIALISTA_CANCELADOS,
                    "0");


            call.enqueue(new Callback<DefaultResponse>() {
                @Override
                public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            DefaultResponse message = response.body();
                            defaultResponseDetallesCotizacionDataSource.onCallBackResultado(message);
                        } else {
                            defaultResponseDetallesCotizacionDataSource.onCallBackResultado(null);
                        }
                    }
                }

                @Override
                public void onFailure(Call<DefaultResponse> call, Throwable t) {
                    defaultResponseDetallesCotizacionDataSource.onCallBackResultado(null);
                    Log.d(TAG, "onFailure : " + t.getMessage().toString());
                }
            });
        }*/

        Log.d(TAG,"onEliminarCotizaciones ");

        String tipoPropuestaUnica = "propuestaCotiza";

        Call<DefaultResponse> call = loginService.cambiarEstadoEliminadoPropuestaUnica(detallesCotizacionU.getPaisCodigo(),
                detallesCotizacionU.getIdPropuesta(),
                detallesCotizacionU.getUsuarioCodigoPropuesta(),
                Constantes.PROPUESTA_ESTADO_CLIENTE_CANCELADOS,
                tipoPropuestaUnica);


        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        DefaultResponse message = response.body();
                        defaultResponseDetallesCotizacionDataSource.onCallBackResultado(message);
                    } else {
                        defaultResponseDetallesCotizacionDataSource.onCallBackResultado(null);
                    }
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                defaultResponseDetallesCotizacionDataSource.onCallBackResultado(null);
                Log.d(TAG, "onFailure : " + t.getMessage().toString());
            }
        });


    }

    @Override
    public void onEliminarPropuestaUnica(DetallesCotizacionUi detallesCotizacionU, final CallBackResultado<DefaultResponse> defaultResponseDetallesCotizacionDataSource) {
        Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        // String pais_codigo = "51";
        String tipoPropuestaUnica = "propuestaunica";

        Call<DefaultResponse> call = loginService.cambiarEstadoEliminadoPropuestaUnica(detallesCotizacionU.getPaisCodigo(),
                detallesCotizacionU.getIdPropuesta(),
                detallesCotizacionU.getUsuarioCodigoPropuesta(),
                Constantes.PROPUESTA_ESTADO_CLIENTE_CANCELADOS,
                tipoPropuestaUnica);


        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        DefaultResponse message = response.body();
                        defaultResponseDetallesCotizacionDataSource.onCallBackResultado(message);
                    } else {
                        defaultResponseDetallesCotizacionDataSource.onCallBackResultado(null);
                    }
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                defaultResponseDetallesCotizacionDataSource.onCallBackResultado(null);
                Log.d(TAG, "onFailure : " + t.getMessage().toString());
            }
        });
    }
}
