package com.application.boxmadikv1.notificaciones.dataSource.remote;

import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.especialista.ListaNotificacionResponse;
import com.application.boxmadikv1.notificaciones.dataSource.NotificacionesDataSource;
import com.application.boxmadikv1.notificaciones.entidad.NotificacionesUi;
import com.application.boxmadikv1.utils.Constantes;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificacionesRemote implements NotificacionesDataSource {

    public static final String TAG = NotificacionesRemote.class.getSimpleName();

    @Override
    public void onMostrarListaNotificaciones(String codigoUsuario, String codigoPais, String codigoGrupoNoti,
                                             int pageCount, String tipoNotificacion,final CallBackResultado<List<NotificacionesUi>> listCallBackResultado) {
        Api service = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        String contadorPaginas = String.valueOf(pageCount);
        Log.d(TAG, "codigoUsuario : " + codigoUsuario +
                " / codigoPais  : " + codigoPais +
                " / codigoGrupoNoti  : " + codigoGrupoNoti +
                " / pageCount  : " + pageCount);
        service.obtenerListaNotiCliente(codigoPais, codigoGrupoNoti, codigoUsuario, contadorPaginas,tipoNotificacion).enqueue(
                new Callback<ListaNotificacionResponse>() {
                    @Override
                    public void onResponse(Call<ListaNotificacionResponse> call, Response<ListaNotificacionResponse> response) {
                        ListaNotificacionResponse resultado = response.body();
                        if (resultado == null) {
                            listCallBackResultado.onCallBackResultado(null);
                            Log.d(TAG, "NotificacionesRemote::resultado == null");
                        } else {
                            if (resultado.getError()) {
                                listCallBackResultado.onCallBackResultado(null);
                                Log.d(TAG, "NotificacionesRemote::" + resultado.getError());
                            } else {
                                List<ListaNotificacionResponse.NotificacioneResponse> notificacioneResponse = resultado.getResponseList();
                                if (notificacioneResponse == null) {
                                    listCallBackResultado.onCallBackResultado(null);
                                    Log.d(TAG, "NotificacionesRemote::else");
                                } else {
                                    /*Aqui cuando la lista esta llena*/
                                    List<NotificacionesUi> notificacionesUiList = new ArrayList<>();
                                    for (ListaNotificacionResponse.NotificacioneResponse
                                            response1 : notificacioneResponse) {
                                        NotificacionesUi notificacionesUi = new NotificacionesUi();
                                        notificacionesUi.setId(response1.getNoti_codigo());
                                        notificacionesUi.setTitulo(response1.getNoti_descripcion());
                                        notificacionesUi.setSubTitulo(response1.getPri_Titulo());
                                        notificacionesUi.setFecha(response1.getNoti_fecha());
                                        notificacionesUi.setTipoNotificacion(response1.getTnot_codigo());

                                        notificacionesUi.setCodigoTipoNotificacion(response1.getTnot_codigo());
                                        notificacionesUi.setCodigoGrupoNotificacion(response1.getGn_codigo());
                                        notificacionesUi.setCodigoPropuesta(response1.getPri_Codigo());
                                        notificacionesUi.setCodigoUsuarioPropuesta(response1.getUsuario_Usu_Codigo());
                                        notificacionesUi.setCodigoUsuarioCotizacion(response1.getUsu_Codigo_Docu());
                                        notificacionesUi.setPropuestaEstado(response1.getPri_Estado());
                                        notificacionesUi.setCotizacionEstado(response1.getCoti_Estado());
                                        notificacionesUi.setUsuCodigoDocu(response1.getUsu_Codigo_Docu());
                                        if (response1.getNoti_leido().equals("1")) {
                                            notificacionesUi.setEstadoLeido(false);
                                        } else {
                                            notificacionesUi.setEstadoLeido(true);
                                        }
                                        notificacionesUiList.add(notificacionesUi);
                                    }

                                    listCallBackResultado.onCallBackResultado(notificacionesUiList);
                                    //  Log.d(TAG, "NotificacionesRemote::else");
                                }
                            }


                        }
                    }

                    @Override
                    public void onFailure(Call<ListaNotificacionResponse> call, Throwable t) {
                        listCallBackResultado.onCallBackResultado(null);
                        Log.d(TAG, "NotificacionesRemote::onFailure ");
                    }
                }
        );
    }
}
