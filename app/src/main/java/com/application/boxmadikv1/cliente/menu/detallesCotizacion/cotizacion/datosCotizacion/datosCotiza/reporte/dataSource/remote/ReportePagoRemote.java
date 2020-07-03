package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.reporte.dataSource.remote;

import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.reporte.dataSource.ReportePagoDataSource;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;
import com.application.boxmadikv1.utils.Constantes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportePagoRemote implements ReportePagoDataSource {

    public static final String TAG = ReportePagoRemote.class.getSimpleName();
    @Override
    public void onAceptarCotizacion(DetallesCotizacionUi detallesCotizacionU, CotizacionesUi cotizacionesUi,final CallBackResultado<DefaultResponse> defaultResponseCallBackResultado) {
        Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        String pais_codigo = detallesCotizacionU.getPaisCodigo();
        //  Constantes.ESTADO_ESPECIALISTA_ACEPTADO);
        Call<DefaultResponse> call = loginService.aceptarCotizacionClienteDeEspecialista(pais_codigo,
                cotizacionesUi.getIdPropuesta(),
                cotizacionesUi.getIdCotizacion(),
                detallesCotizacionU.getUsuarioCodigoPropuesta(),
                cotizacionesUi.getIdUsuarioCotizacion(),
                Constantes.PROPUESTA_ESTADO_CLIENTE_FINALIZADO,
                Constantes.ESTADO_ESPECIALISTA_PAGADO,
                "0");
//                Constantes.PROPUESTA_ESTADO_CLIENTE_PROCESO,
//                Constantes.ESTADO_ESPECIALISTA_PAGADO);
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

    @Override
    public void onEnviarNotificacionCoti(String notiEstado, String tipoNotiCodigo, String grupoNotiCodigo, String propuestaCodigo, String cotizacionCodigo, String paisCodigo,final CallBackResultado<DefaultResponse> defaultResponseCallBackResultado) {
        Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);

        Call<DefaultResponse> call = loginService.envioNotificacionCotizacionAceptada(notiEstado,
                tipoNotiCodigo,
                grupoNotiCodigo,
                propuestaCodigo,
                cotizacionCodigo,
                paisCodigo);
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
