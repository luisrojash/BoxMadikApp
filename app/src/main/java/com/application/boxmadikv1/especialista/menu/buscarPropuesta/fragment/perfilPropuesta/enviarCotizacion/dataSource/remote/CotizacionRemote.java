package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.enviarCotizacion.dataSource.remote;

import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.CambioResponse;
import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.DefaultResponseRegistro;
import com.application.boxmadikv1.api.response.especialista.DatosCotizacionResponse;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.enviarCotizacion.dataSource.CotizacionDataSource;
import com.application.boxmadikv1.utils.Constantes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CotizacionRemote implements CotizacionDataSource {

    public static final String TAG = CotizacionRemote.class.getSimpleName();

    @Override
    public void onObtenerComisionPorcentaje(CallBackResultado<String> stringCallBackResultado) {

    }

    @Override
    public void onObtenerTipoCambioDolar(final CallBackResultado<String> stringCallBackResultado,String codigoPais) {
        String baseUrl = "http://free.currencyconverterapi.com/api/v5/";
        // String baseRemote = "http://diazosorio.com/BoxMyApi/api/";
        String tipoMoneda = "USD_PEN";
        String ultra = "ultra";
        Api apiService = RetrofitClient.createService(Api.class, baseUrl);
        Call<CambioResponse> call = apiService.obtenerCambioDolarActual(tipoMoneda, ultra);
        call.enqueue(new Callback<CambioResponse>() {
            @Override
            public void onResponse(Call<CambioResponse> call, Response<CambioResponse> response) {
                CambioResponse cambioResponse = response.body();
                if (cambioResponse == null) return;
                stringCallBackResultado.onCallBackResultado(cambioResponse.getMessage());

            }

            @Override
            public void onFailure(Call<CambioResponse> call, Throwable t) {
                stringCallBackResultado.onCallBackResultado(null);
                Log.d(TAG, "onObtenerTipoCambioDolar::cambioResponse : " + t.getMessage());
            }
        });
    }

    @Override
    public void onEnviarCotizacion(String descripcion, double coti_montoNeto, double coti_comisionTotal, double coti_sumaTotalSoles, double coti_sumaTotalDolares, double monedaDolar, String codigoUsuario, String codigo_propuesta, String dateInicio, String dateFin, String boxmadik_comision, final CallBackResultado<DefaultResponseRegistro> defaultResponseCallBackResultado) {

        String baseUrl = "http://free.currencyconverterapi.com/api/v5/";
        // String baseRemote = "http://diazosorio.com/BoxMyApi/api/";
        String codigo_pais = "51";


        Log.d(TAG, "Enviar Cotizacion " +
                "descripcion +" + descripcion + "/ cotiMOnoto" + coti_montoNeto + "/ coti_comisionTotal" + coti_comisionTotal + "/ coti_sumaTotalSoles" + coti_sumaTotalSoles + "/ coti_sumaTotalDolares" + coti_sumaTotalDolares + "/ monedaDolar" + monedaDolar + "/ codigoUsuario" + codigoUsuario + "/ codigo_propuesta" + codigo_propuesta + "/ dateInicio" + dateInicio + "/ dateFin" + dateFin + "/ boxmadik_comision" + boxmadik_comision);

        Api apiService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);

        String stringCotiMontoNeto = String.valueOf(coti_montoNeto);
        String stringCotiComisionTOtal = String.valueOf(coti_comisionTotal);
        String stringCotiSumaTotales = String.valueOf(coti_sumaTotalSoles);
        String stringCotiSumaDolares = String.valueOf(coti_sumaTotalDolares);
        String stringTipoCambio = String.valueOf(monedaDolar);

        Log.d(TAG, "codigo_pais " + codigo_pais + " / " + codigoUsuario + " / CODIGO PROPUESTA " + codigo_propuesta);

        Call<DefaultResponseRegistro> call = apiService.enviarCotizacionPropuesta(descripcion,
                stringCotiMontoNeto,
                stringCotiComisionTOtal,
                stringCotiSumaTotales,
                stringCotiSumaDolares,
                codigoUsuario,
                stringTipoCambio,
                codigo_propuesta,
                codigo_pais,
                dateInicio,
                dateFin,
                boxmadik_comision
        );
        call.enqueue(new Callback<DefaultResponseRegistro>() {
            @Override
            public void onResponse(Call<DefaultResponseRegistro> call, Response<DefaultResponseRegistro> response) {
                DefaultResponseRegistro cambioResponse = response.body();
                if (cambioResponse == null) return;
                defaultResponseCallBackResultado.onCallBackResultado(cambioResponse);
                // stringCallBackResultado.onCallBackResultado(cambioResponse.getMessage());

            }

            @Override
            public void onFailure(Call<DefaultResponseRegistro> call, Throwable t) {
                defaultResponseCallBackResultado.onCallBackResultado(null);
                // stringCallBackResultado.onCallBackResultado(null);
                Log.d(TAG, "onEnviarCotizacion::cambioResponse : " + t.getMessage());
            }
        });
    }

    @Override
    public void onObtenerValidacionCotizacion(String usuarioCodigo, String propuestaCodigo, String estadoCotizacion, String paisCodigo, final CallBackResultado<DatosCotizacionResponse> datosCotizacionResponseCallBackResultado) {

        Api apiService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        Call<DatosCotizacionResponse> call = apiService.obtenerValidacionCotizacion(
                usuarioCodigo, estadoCotizacion, propuestaCodigo, paisCodigo);
        call.enqueue(new Callback<DatosCotizacionResponse>() {
            @Override
            public void onResponse(Call<DatosCotizacionResponse> call, Response<DatosCotizacionResponse> response) {
                DatosCotizacionResponse cambioResponse = response.body();
                //if (cambioResponse == null) return;
                datosCotizacionResponseCallBackResultado.onCallBackResultado(cambioResponse);
            }

            @Override
            public void onFailure(Call<DatosCotizacionResponse> call, Throwable t) {
                datosCotizacionResponseCallBackResultado.onCallBackResultado(null);
                // stringCallBackResultado.onCallBackResultado(null);
                Log.d(TAG, "onObtenerValidacionCotizacion::cambioResponse : " + t.getMessage());
            }
        });
    }

    @Override
    public void onEnvioNotificacion(String grupoNotificacionCodigo, String tipoNotificacionCodigo,
                                    String usuarioCodigo, String propuestaCodigo, String paisCodigo,String codigoCotizacion) {

        Log.d(TAG, "onEnvioNotificacion  grupoNotificacionCodigo: "+grupoNotificacionCodigo+
                    " :: tipoNotificacionCodigo :: "+ tipoNotificacionCodigo+
                    " :: usuarioCodigo :: "+ usuarioCodigo+
                    " :: propuestaCodigo :: "+ propuestaCodigo+
                    " :: paisCodigo :: "+ codigoCotizacion);

        Api apiService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        Call<DefaultResponse> call = apiService.envioNotificacionCotizacion(grupoNotificacionCodigo, tipoNotificacionCodigo, usuarioCodigo, propuestaCodigo, paisCodigo,codigoCotizacion);
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse cambioResponse = response.body();
                if (cambioResponse == null) return;
               // datosCotizacionResponseCallBackResultado.onCallBackResultado(cambioResponse);
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
               // datosCotizacionResponseCallBackResultado.onCallBackResultado(null);
                // stringCallBackResultado.onCallBackResultado(null);
                Log.d(TAG, "onEnvioNotificacion::cambioResponse : " + t.getMessage());
            }
        });
    }
}
