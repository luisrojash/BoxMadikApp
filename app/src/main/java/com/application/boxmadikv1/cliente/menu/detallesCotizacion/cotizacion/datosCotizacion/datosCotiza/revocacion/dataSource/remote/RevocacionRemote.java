package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.dataSource.remote;

import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.dataSource.RevocacionDataSource;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.entidad.TipoCalidadTrabajoUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.entidad.TipoMotivoRevocacionUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.entidad.TipoSolicitaRevocanteUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;
import com.application.boxmadikv1.utils.Constantes;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RevocacionRemote implements RevocacionDataSource {

    public static final String TAG = RevocacionRemote.class.getSimpleName();

    @Override
    public void onMostrarListaTipoCalidad(CallBackResultado<List<TipoCalidadTrabajoUi>> listCallBackResultado) {

    }

    @Override
    public void onMostrarListaTipoMotivoRevocacion(CallBackResultado<List<TipoMotivoRevocacionUi>> listCallBackResultado) {

    }

    @Override
    public void onMostrarListaTipoSolicitaRevocante(CallBackResultado<List<TipoSolicitaRevocanteUi>> listCallBackResultado) {

    }

    @Override
    public void onRegistrarRevocacion(String idTipoMotivoRevocacion,
                                      String idTipoCalidadTrabajo,
                                      String valorPorcentajeTrabajo,
                                      String idSolicitaRevocante,
                                      String observacion,
                                      DetallesCotizacionUi detallesCotizacionUi,
                                      CotizacionesUi cotizacionesUi, final CallBackResultado<DefaultResponse> defaultResponseCallBackResultado) {
        Api apiService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);

        String idCrearRevocacionUsuario = detallesCotizacionUi.getKeyUser();
        String idUsuarioRevocacionResponde = cotizacionesUi.getIdUsuarioCotizacion();
        String idPropuestaInicial = detallesCotizacionUi.getIdPropuesta();
        String valorPorcentajeString = String.valueOf(valorPorcentajeTrabajo);

        Log.d(TAG, "paisCodigo: " + detallesCotizacionUi.getPaisCodigo());

        /*Call<DefaultResponse> call = apiService.registrarRevocacionCliente(observacion,
                valorPorcentajeString,
                idSolicitaRevocante,
                idTipoMotivoRevocacion,
                idPropuestaInicial,
                idCrearRevocacionUsuario,
                idUsuarioRevocacionResponde,
                idTipoCalidadTrabajo,
                detallesCotizacionUi.getPaisCodigo());*/

        Call<DefaultResponse> call = apiService.registrarRevocacionCliente(observacion,
                valorPorcentajeString,
                idSolicitaRevocante,
                idTipoMotivoRevocacion,
                idPropuestaInicial,
                idCrearRevocacionUsuario,
                idUsuarioRevocacionResponde,
                idTipoCalidadTrabajo,
                detallesCotizacionUi.getPaisCodigo());

        Log.d(TAG, "idTipoMotivoRevocacion : " + idTipoMotivoRevocacion +
                "   idTipoCalidadTrabajo : " + idTipoCalidadTrabajo +
                "   valorPorcentajeTrabajo : " + valorPorcentajeTrabajo +
                "   idSolicitaRevocante : " + idSolicitaRevocante +
                "   observacion : " + observacion);
/*
        Call<DefaultResponse> call = apiService.registrarRevocacionCliente(observacion,
                valorPorcentajeString,
                idSolicitaRevocante,
                idTipoMotivoRevocacion,
                idPropuestaInicial,
                idCrearRevocacionUsuario,
                idUsuarioRevocacionResponde,
                idTipoCalidadTrabajo,
                detallesCotizacionUi.getPaisCodigo());*/
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse cambioResponse = response.body();
                if (cambioResponse == null) return;
                defaultResponseCallBackResultado.onResultado(cambioResponse);
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                defaultResponseCallBackResultado.onResultado(null);
                Log.d(TAG, "cambioResponse : " + t.getMessage());
            }
        });
    }

    @Override
    public void onCambiarEstadoPropuestaYCoti(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi, final CallBackResultado<DefaultResponse> defaultResponseCallBackResultado) {
        Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        String pais_codigo = detallesCotizacionUi.getPaisCodigo();
        Call<DefaultResponse> call = loginService.aceptarCotizacionClienteDeEspecialista(pais_codigo,
                cotizacionesUi.getIdPropuesta(),
                cotizacionesUi.getIdCotizacion(),
                detallesCotizacionUi.getUsuarioCodigoPropuesta(),
                cotizacionesUi.getIdUsuarioCotizacion(),
                Constantes.PROPUESTA_ESTADO_CLIENTE_REVOCADOS,
                Constantes.ESTADO_ESPECIALISTA_ACEPTADO,
                "0");
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        DefaultResponse message = response.body();
                        defaultResponseCallBackResultado.onResultado(message);
                    } else {
                        defaultResponseCallBackResultado.onResultado(null);
                    }
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                defaultResponseCallBackResultado.onResultado(null);
                Log.d(TAG, "onFailure : " + t.getMessage().toString());
            }
        });
    }

    @Override
    public void onEnvioNotificacion(String grupoNotificacionCodigo, String tipoNotificacionCodigo,
                                    String usuarioCodigo, String propuestaCodigo, String paisCodigo,
                                    String codigoCotizacion, final CallBackResultado<DefaultResponse> defaultResponseCallBackResultado) {
        Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        String notificacionCliente = "notifiCliente";
        Call<DefaultResponse> call = loginService.envioNotificacionRevocacion(grupoNotificacionCodigo,
                tipoNotificacionCodigo,
                usuarioCodigo,
                propuestaCodigo,
                paisCodigo,
                codigoCotizacion,
                Constantes.ESTADO_ACTIVO,
                notificacionCliente);
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        DefaultResponse message = response.body();
                        defaultResponseCallBackResultado.onResultado(message);
                    } else {
                        defaultResponseCallBackResultado.onResultado(null);
                    }
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                defaultResponseCallBackResultado.onResultado(null);
                Log.d(TAG, "onFailure : " + t.getMessage().toString());
            }
        });
    }
}
