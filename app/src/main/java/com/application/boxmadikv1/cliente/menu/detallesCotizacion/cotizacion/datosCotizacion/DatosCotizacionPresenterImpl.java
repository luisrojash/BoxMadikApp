package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.activity.BaseActivityPresenterImpl;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.DetallesCotizaFragment;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.RevocacionPresenterImpl;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;
import com.application.boxmadikv1.utils.Constantes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DatosCotizacionPresenterImpl extends BaseActivityPresenterImpl<DatosCotizacionView> implements DatosCotizacionPresenter {

    public static final String TAG = DatosCotizacionPresenterImpl.class.getSimpleName();


    public DatosCotizacionPresenterImpl(UseCaseHandler handler, Resources res) {
        super(handler, res);
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onStart() {
        super.onStart();
        validarTipoNotificaciones(extras);
        iniciarFragmentos();
        mostrarDataInicial();
        validacionActualizarNotificacion(tipoLlegadaExtra);
        validarTipoEstadosPropuesta();
    }

    private void validacionActualizarNotificacion(String tipoLlegadaExtra) {
        if (tipoLlegadaExtra == null) return;
        switch (tipoLlegadaExtra) {
            case "notificacionClicked": // Actualizamos el estado notificacion a Leido
                /*initRetrofitActualizarEstado(Constantes.PAIS_CODIGO_PERU, Constantes.TIPO_NOTIFICACION_CREACION_PROPUESTA,
                        Constantes.GRUPO_NOTIFICACION_CLIENTE, itemUi.getCodigoUsuarioPropuesta(), itemUi.getCodigoPropuesta());
               */
                Log.d(TAG, "Aqui actualiza el estado de la notificacion a leido");
                break;
            case "actividadClicked":
                Log.d(TAG, "actividadClicked");
                break;
            case "actividadNotiNoti":/*Aqui cuando llega de Seleccionar Usuario Actualizamos a Leido*/
                initRetrofitActualizarEstado(detallesCotizacionUi.getPaisCodigo(), Constantes.TIPO_NOTIFICACION_CREACION_COTIZACION,
                        Constantes.GRUPO_NOTIFICACION_ESPECIALISTA, detallesCotizacionUi.getUsuarioCodigoPropuesta(), detallesCotizacionUi.getIdPropuesta());
                break;
            default:
                //if (view != null) view.finishActivity();
                break;
        }
    }

    private void initRetrofitActualizarEstado(String paisCodigo, String tipoNotificacionCreacionPropuesta, String grupoNotificacionCliente, String usuarioCodigoPropuesta, String idPropuesta) {
            Log.d(TAG, " paisCodigo : "+ paisCodigo+
                    " tipoNotificacionCreacionPropuesta : "+ tipoNotificacionCreacionPropuesta+
                    " grupoNotificacionCliente : "+ grupoNotificacionCliente+
                    " usuarioCodigoPropuesta : "+ usuarioCodigoPropuesta+
                    " idPropuesta : "+ idPropuesta
            );


        Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        loginService.actualizarNotificacionesLeidas(paisCodigo,
                tipoNotificacionCreacionPropuesta,
                grupoNotificacionCliente,
                usuarioCodigoPropuesta,
                idPropuesta
        ).enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                // DefaultResponse loginResponse = response.body();
                DefaultResponse defaultResponse = response.body();
                if (defaultResponse == null) {
                    String mensaje = "Ocurrio Algun Error";
                    Log.d(TAG, mensaje);
                    return;
                }
                if (defaultResponse != null) {
                    if (defaultResponse.getError()) {
                        Log.d(TAG, "ALGO PASU PPAU");
                    } else {
                        Log.d(TAG, "ACTUALIZO CORRECTAMENTE");
                    }
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                String mensaje = "Ocurrio Algun Error";
                Log.d(TAG, "mensaje : " + mensaje);
                return;
                //Log.d(TAG, "onFailure : " + t.getMessage().toString());
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        bundleasd = null;
    }

    Bundle bundleasd;

    private void validarTipoNotificaciones(Bundle extras) {
        this.bundleasd = extras;
        if (extras == null) return;
        String tipoEstado = extras.getString("estado");
        if (tipoEstado == null) return;
        switch (tipoEstado) {
            case RevocacionPresenterImpl.CREADO_CORRECTAMENTE_REVOCACION_CLIENTE:
                if (view != null) view.mostrarMensaje("Revocación Enviada");
                break;
        }
    }

    private void mostrarDataInicial() {
        if (view != null) {
            view.mostrarDataInicial(detallesCotizacionUi.getImageRubro(),
                    detallesCotizacionUi.getNombreProyecto(),
                    detallesCotizacionUi.getFechaPropuesta(),
                    detallesCotizacionUi.getNombreDepartamento()+" - " +detallesCotizacionUi.getNombreDistrito());
        }
    }

    private void iniciarFragmentos() {
        if (view != null) {
            view.mostrarFragmentos(detallesCotizacionUi, cotizacionesUi);
        }
    }

    DetallesCotizacionUi detallesCotizacionUi;
    CotizacionesUi cotizacionesUi;
    String tipoLlegadaExtra;

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;
        this.detallesCotizacionUi = extras.getParcelable(Constantes.EXTRAS_CLIENTE_DETALLES_PROPUESTA);
        this.cotizacionesUi = extras.getParcelable(DetallesCotizaFragment.EXTRA_DETALLES_COTIZA_COTIZACIONESUI);
        Log.d(TAG, "detallesCotizacionUi : " + detallesCotizacionUi.getNombreProyecto() +
                "detallesCotizacionUi : " + cotizacionesUi.getNombreEspecialista());
        this.tipoLlegadaExtra = extras.getString("notificacion");

    }

    private void validarTipoEstadosPropuesta() {
        switch (detallesCotizacionUi.getTipoEstado()) {
            case DetallesCotizacionUi.ESTADO_CANCELADO:
                Log.d(TAG, "ESTADO_CANCELADO");
                break;
            case DetallesCotizacionUi.ESTADO_PENDIENTE:
                Log.d(TAG, "ESTADO_PENDIENTE");
                break;
            case DetallesCotizacionUi.ESTADO_PROCESO:
                Log.d(TAG, "ESTADO_PROCESO");
                break;
            case DetallesCotizacionUi.ESTADO_FINALIZADO:
                Log.d(TAG, "ESTADO_FINALIZADO");
                break;
            case DetallesCotizacionUi.ESTADO_PAGADOS:
                Log.d(TAG, "ESTADO_PAGADOS");
                break;
            default:
                Log.d(TAG, "default");
                break;
        }
    }


    @Override
    public void onClickClose() {
        if (tipoLlegadaExtra == null) {
            Log.d(TAG, "tipoLlegadaExtra");
            if (view != null) view.finishActivity(detallesCotizacionUi,cotizacionesUi);
            return;
        } else {
            switch (tipoLlegadaExtra) {
                case "notificacionClicked": // Actualizamos el estado notificacion a Leido
                    if (view != null) view.startActivityMenuPrincipalCliente();
                    Log.d(TAG, "Aqui actualiza el estado de la notificacion a leido");
                    break;
                case "actividadClicked":
                    Log.d(TAG, "actividadClicked");
                    //if (view != null) view.finishActivity(detallesCotizacionUi,cotizacionesUi);

                    break;
                case "actividadNotiNoti":
                    Log.d(TAG, "actividadNotiNoti");
                  //  if (view != null) view.finishActivity(detallesCotizacionUi,cotizacionesUi);
                    if(view!=null)view.onFinishStartNotificacion(detallesCotizacionUi,cotizacionesUi);
                    break;
                default:
                    Log.d(TAG, "default");
                    if (view != null) view.finishActivity(detallesCotizacionUi,cotizacionesUi);
                    break;
            }
        }

    }
}
