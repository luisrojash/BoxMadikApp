package com.application.boxmadikv1.rptRevocacion;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.especialista.ObtenerRespuestaRevocaResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.activity.BaseActivityPresenterImpl;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.DetallesCotizaFragment;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.ItemUi;
import com.application.boxmadikv1.rptRevocacion.entidad.PropuestaRevocacionUi;
import com.application.boxmadikv1.rptRevocacion.useCase.GuardarRespuestRevocacion;
import com.application.boxmadikv1.rptRevocacion.useCase.ListaPropuestaRevocacion;
import com.application.boxmadikv1.rptRevocacion.useCase.ObtenerRespuestaRevoca;
import com.application.boxmadikv1.utils.Constantes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.application.boxmadikv1.registraUser.RegistrarUsuarioPresenterImpl.REGISTRO_USUARIO_CORRECTAMENTE;

public class RespuestaRevocacionPresenterImpl extends BaseActivityPresenterImpl<RespuestaRevocacionView> implements RespuestaRevocacionPresenter {

    public static final String TAG = RespuestaRevocacionPresenterImpl.class.getSimpleName();
    private ListaPropuestaRevocacion listaPropuestaRevocacion;
    private GuardarRespuestRevocacion guardarRespuestRevocacion;
    private ObtenerRespuestaRevoca obtenerRespuestaRevoca;

    public RespuestaRevocacionPresenterImpl(UseCaseHandler handler, Resources res, ListaPropuestaRevocacion listaPropuestaRevocacion, GuardarRespuestRevocacion guardarRespuestRevocacion, ObtenerRespuestaRevoca obtenerRespuestaRevoca) {
        super(handler, res);
        this.listaPropuestaRevocacion = listaPropuestaRevocacion;
        this.guardarRespuestRevocacion = guardarRespuestRevocacion;
        this.obtenerRespuestaRevoca = obtenerRespuestaRevoca;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onBackPressed() {

    }

    String tipousuario;
    ItemUi itemUi;

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;
        tipousuario = extras.getString("tipousuario");

    }

    @Override
    public void onStart() {
        super.onStart();
        validarTipoUsuario(tipousuario);
    }

    private DetallesCotizacionUi detallesCotizacionUi;
    private CotizacionesUi cotizacionesUi;

    private void validarTipoUsuario(String tipousuario) {
        switch (tipousuario) {
            case "cliente":
                detallesCotizacionUi = extras.getParcelable(Constantes.EXTRAS_CLIENTE_DETALLES_PROPUESTA);
                cotizacionesUi = extras.getParcelable(DetallesCotizaFragment.EXTRA_DETALLES_COTIZA_COTIZACIONESUI);
                validarTipoLlegada(extras);
                Log.d(TAG, "detallesCotizacionUi : " + detallesCotizacionUi.getUsuarioCodigoPropuesta());
                if (view != null) view.mostrarProgressBarDialog("Cargando Datos");
                initObtenerRespuestaRevocacion();
                break;
            case "especialista":
                /*Aqui los datos muestra vacio
                 * Por que especialista siempre va responder , segun el flujo*/
                itemUi = extras.getParcelable("itemUi");
                initUseCasePropuestaRevocacionLista();
                break;
            default:
                Log.d(TAG, "default");
                break;

        }
    }

    private void validarTipoLlegada(Bundle extras) {
        String tipoLlegada = extras.getString("tipoLlegada");
        if(tipoLlegada==null)return;
        switch (tipoLlegada) {
            case "notificacionRespuestaRevocaCliente":
                initRetrofitActualizarEstado(detallesCotizacionUi.getPaisCodigo(), Constantes.TIPO_NOTIFICACION_RESPUESTA_NOTIFICACION,
                        Constantes.GRUPO_NOTIFICACION_CLIENTE, detallesCotizacionUi.getUsuarioCodigoPropuesta(), detallesCotizacionUi.getIdPropuesta());

                break;
            default:
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

    private void initObtenerRespuestaRevocacion() {
        handler.execute(obtenerRespuestaRevoca, new ObtenerRespuestaRevoca.RequestValues(
                detallesCotizacionUi.getIdPropuesta(),
                detallesCotizacionUi.getUsuarioCodigoPropuesta(),
                cotizacionesUi.getIdUsuarioCotizacion(),
                "51"), new UseCase.UseCaseCallback<ObtenerRespuestaRevoca.ResponseValue>() {
            @Override
            public void onSuccess(ObtenerRespuestaRevoca.ResponseValue response) {
                if (response != null) {
                    ObtenerRespuestaRevocaResponse revocaResponse = response.getObtenerRespuestaRevocaResponse();
                    if (revocaResponse.getError()) {
                        /*Por hubo algun error*/
                        if (view != null) view.enviarMenuPrinicipal();
                        Log.d(TAG, "Datos no trajeron correctamente");
                    } else {
                        ObtenerRespuestaRevocaResponse.RespuestaRevocaResponse response1 = revocaResponse.getRespuestaRevocaResponse();
                        if (response1 == null) return;
                        validaTipoRespuesta(response1);
                        if (view != null) {
                            view.deshabilitarWidgetCompleto();
                            view.mostrarDataObtenida(response1.getDescripcion_resp(), response1.getObserva_resp());
                            view.ocultarProgressBarDialog();
                        }

                    }
                } else {
                    if (view != null) view.enviarMenuPrinicipal();
                    Log.d(TAG, "Datos Vacíos");
                }
            }

            @Override
            public void onError() {

            }
        });
    }

    private void validaTipoRespuesta(ObtenerRespuestaRevocaResponse.RespuestaRevocaResponse response1) {
        switch (response1.getRespuesta_resp()) {
            case "Si":
                if (view != null) view.mostrarCheckSi();
                break;
            case "No":
                if (view != null) view.mostrarCheckNo();
                break;
        }
    }

    private void initUseCasePropuestaRevocacionLista() {
        handler.execute(listaPropuestaRevocacion, new ListaPropuestaRevocacion.RequestValues(),
                new UseCase.UseCaseCallback<ListaPropuestaRevocacion.ResponseValue>() {
                    @Override
                    public void onSuccess(ListaPropuestaRevocacion.ResponseValue response) {
                        if (view != null)
                            view.mostrarListaPropuestaRevocacion(response.getDocumentoUiList());
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    String propuestaRevocacionUi;

    @Override
    public void onSpinnerTipoPropuestaRevocacion(String propuestaRevocacionUiID) {
        this.propuestaRevocacionUi = propuestaRevocacionUiID;

    }

    String tipoRespuesta = null;

    @Override
    public void onClickButtonSi() {
        this.tipoRespuesta = "Si";
        if (view != null) {
            view.mostrarCheckSi();
            view.ocultarCheckNo();
        }
    }

    @Override
    public void onClickButtonNo() {
        this.tipoRespuesta = "No";
        if (view != null) {
            view.mostrarCheckNo();
            view.ocultarCheckSi();
        }

    }

    @Override
    public void onClickEnviarRespuesta(String detalleRespuesta) {
        if (view != null) view.deshabilitarButtonEnviar();
        if (tipoRespuesta == null) {
            if (view != null) {
                String mensaje = "Seleccione la Respuesta!!!";
                view.mostrarMensaje(mensaje);
                view.habilitarButtonEnviar();
            }
            return;
        }
        if (propuestaRevocacionUi == null) {
            if (view != null) {
                String mensaje = "Selecione el Tipo Propuesta";
                view.mostrarMensaje(mensaje);
                view.habilitarButtonEnviar();
            }
            return;
        }
        if(propuestaRevocacionUi.equals("0")){
            if (view != null) {
                String mensaje = "Selecione el Tipo Propuesta";
                view.mostrarMensaje(mensaje);
                view.habilitarButtonEnviar();
            }
            return;
        }
        if (detalleRespuesta.isEmpty() || detalleRespuesta.equals(" ")) {
            if (view != null) {
                String mensaje = "Llene los campos o escriba correctamente";
                view.mostrarMensajeErroDetalles(mensaje);
                view.habilitarButtonEnviar();
            }
            return;
        }

      //  String validarDetalleRespuesta = Constantes.isResultadoEspecial(detalleRespuesta);

        String removeAcentosCadena = Constantes.removeAcentos(detalleRespuesta);
        String isPrimeroResultadoCharacter = Constantes.isPrimeroResultadoCharacter(removeAcentosCadena);
        String isSegundoresultadoCharacter = Constantes.isSegundoresultadoCharacter(isPrimeroResultadoCharacter);
        initUseCaseGuardarDataResponse(tipoRespuesta,
                propuestaRevocacionUi,
                isSegundoresultadoCharacter);
    }

    private void initUseCaseGuardarDataResponse(String tipoRespuesta, String propuestRevocacionId, String detalleRespuesta) {
        String propuestaId = itemUi.getCodigoPropuesta();
        String codigoUsuarioPropuesta = itemUi.getCodigoUsuarioPropuesta();
        String codigoUsuarioCotizacion = itemUi.getIdUsuarioCotizacion();
        if (view != null) view.mostrarProgressBarDialog("Verificando el Registro Espere..");
        handler.execute(guardarRespuestRevocacion, new GuardarRespuestRevocacion.RequestValues(tipoRespuesta,
                propuestRevocacionId, detalleRespuesta,
                propuestaId,
                codigoUsuarioPropuesta,
                codigoUsuarioCotizacion), new UseCase.UseCaseCallback<GuardarRespuestRevocacion.ResponseValue>() {
            @Override
            public void onSuccess(GuardarRespuestRevocacion.ResponseValue response) {
                if (response != null) {
                    if (response.getDefaultResponse() == null) {
                        return;
                    }
                    if (response.getDefaultResponse().getError()) {
                        if (view != null) {
                            view.mostrarMensaje("Ocurrio algun Problema intentelo mas tarde");
                            view.ocultarProgressBarDialog();
                            view.habilitarButtonEnviar();
                        }
                        return;
                    } else {
                        if (view != null) {
                            /*Agrega Usuario*/
                            // view.mostrarMensaje(response.getDefaultResponse().getMessage());
                            view.ocultarProgressBarDialog();
                            view.starMainActivityEspec();
                            view.habilitarButtonEnviar();
                        }
                        return;
                    }
                } else {
                    if (view != null) {
                        view.mostrarMensaje("Intentelo de nuevo o compruebe su conexión");
                        view.ocultarProgressBarDialog();
                        view.habilitarButtonEnviar();
                    }
                    return;
                }
            }

            @Override
            public void onError() {

            }
        });
        // Log.d(TAG, "Propuesta_inicial : " +

    }
}
