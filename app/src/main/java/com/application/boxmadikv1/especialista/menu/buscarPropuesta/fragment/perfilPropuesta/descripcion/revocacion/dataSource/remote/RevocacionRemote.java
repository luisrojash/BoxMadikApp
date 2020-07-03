package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion.dataSource.remote;

import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.ItemUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion.dataSource.RevocacionDataSource;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion.entidad.TipoMotivoRevocacionUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion.entidad.TipoSolucionRevocacionUi;
import com.application.boxmadikv1.utils.Constantes;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RevocacionRemote implements RevocacionDataSource {

    public static final String TAG = RevocacionRemote.class.getSimpleName();

    @Override
    public void onListarTipoMotivoRevocacion(CallBackResultado<List<TipoMotivoRevocacionUi>> tipoMotivoRevocacionUiCallBackResultado) {

    }

    @Override
    public void onListarTipoSolucionRevocacion(CallBackResultado<List<TipoSolucionRevocacionUi>> listCallBackResultado) {

    }

    @Override
    public void onRegistrarRevocacion(ItemUi itemUi, String idTipSolucionRevocacion, String idTipoMotivoRevocacion, int valorPorcentajeTrabajo, String observacion, final CallBackResultado<DefaultResponse> defaultResponseCallBackResultado) {
        Api apiService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);

        String idCrearRevocacionUsuario = itemUi.getKeyUser();
        String idUsuarioRevocacionResponde = itemUi.getCodigoUsuarioPropuesta();
        String idPropuestaInicial = itemUi.getCodigoPropuesta();
        String valorPorcentajeString = String.valueOf(valorPorcentajeTrabajo);

        Call<DefaultResponse> call = apiService.registrarRevocacionEspecialista(observacion,
                valorPorcentajeString,
                idTipSolucionRevocacion,
                idTipoMotivoRevocacion,
                idPropuestaInicial,
                idCrearRevocacionUsuario,
                idUsuarioRevocacionResponde,
                itemUi.getPaisCodigo());
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse cambioResponse = response.body();
                if (cambioResponse == null) return;
                defaultResponseCallBackResultado.onCallBackResultado(cambioResponse);
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                defaultResponseCallBackResultado.onCallBackResultado(null);
                Log.d(TAG, "cambioResponse : " + t.getMessage());
            }
        });
    }

    @Override
    public void onEnvioNotificacion(String grupoNotificacionCodigo, String tipoNotificacionCodigo, String usuarioCodigo, String propuestaCodigo, String paisCodigo, String codigoCotizacion, CallBackResultado<DefaultResponse> defaultResponseCallBackResultado) {

    }
}
