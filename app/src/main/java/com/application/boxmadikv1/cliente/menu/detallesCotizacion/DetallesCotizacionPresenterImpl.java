package com.application.boxmadikv1.cliente.menu.detallesCotizacion;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.activity.BaseActivityPresenterImpl;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.useCase.EliminarPropuesta;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.useCase.EliminarPropuestaUnica;
import com.application.boxmadikv1.utils.Constantes;

import java.util.ArrayList;
import java.util.List;

public class DetallesCotizacionPresenterImpl extends BaseActivityPresenterImpl<DetallesCotizacionView> implements DetallesCotizacionPresenter {

    public static final String ELIMINADO_CORRECTAMENTE_PROPUESTA = "DetallesCotizacionPresenterImpl";

    public static final String TIPO_ELIMINADO_PROPUESTA_UNICA = "elimarPropuesta";
    public static final String TIPO_ELIMINADO_PROPUESTA_COTIZACIONES = "eliminarGlobal";

    public static final String TAG = DetallesCotizacionPresenterImpl.class.getSimpleName();

    private EliminarPropuesta eliminarPropuesta;
    private EliminarPropuestaUnica eliminarPropuestaUnica;


    public DetallesCotizacionPresenterImpl(UseCaseHandler handler, Resources res, EliminarPropuesta eliminarPropuesta, EliminarPropuestaUnica eliminarPropuestaUnica) {
        super(handler, res);
        this.eliminarPropuesta = eliminarPropuesta;
        this.eliminarPropuestaUnica = eliminarPropuestaUnica;
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
        iniciarFragmentos();
        validarTipoEstadosPropuesta();
        if (view != null)
            view.mostrarDataInicial(detallesCotizacionUi.getImageRubro(),
                    detallesCotizacionUi.getNombreProyecto(),
                    detallesCotizacionUi.getFechaPropuesta(),
                    detallesCotizacionUi.getNombreDepartamento()+" - " +detallesCotizacionUi.getNombreDistrito());
    }

    private void iniciarFragmentos() {
        if (view != null) {

            //Log.d(TAG, "keyUser ; " + keyUser);
            view.mostrarFragmentos(detallesCotizacionUi);

        }
    }


    DetallesCotizacionUi detallesCotizacionUi;

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;
        /*this.codigoPropuesta = extras.getString(Constantes.EXTRAS_CLIENTE_CODIGO_PROPUESTA);
        this.nombrePropuesta = extras.getString(Constantes.EXTRAS_CLIENTE_NOMBRE_PROPUESTA);
        this.imagenRubro = extras.getString(Constantes.EXTRAS_CLIENTE_IMAGEN_RUBRO);
        this.fechaPropuesta = extras.getString(Constantes.EXTRAS_CLIENTE_FECHA_PROPUESTA);
        this.tipoEstado = extras.getInt(Constantes.EXTRAS_CLIENTE_ESTADO_PROPUESTA);*/
        this.detallesCotizacionUi = extras.getParcelable(Constantes.EXTRAS_CLIENTE_DETALLES_PROPUESTA);

        // initUseCasePropuestaInicial();


        Log.d(TAG, "codigoPropuesta : " + detallesCotizacionUi.getUsuarioCodigoPropuesta());

    }


    private void validarTipoEstadosPropuesta() {
        //if (view != null) view.mostrarProgressBar();

        switch (detallesCotizacionUi.getTipoEstado()) {
            case DetallesCotizacionUi.ESTADO_CANCELADO:
                if (view != null) view.mostrarImagenEliminar();
                break;
            case DetallesCotizacionUi.ESTADO_PENDIENTE:
                Log.d(TAG, "validarTipoEstadosPropuestaESTADO_PENDIENTE");
                if (view != null) view.mostrarImagenEliminar();
                break;
            case DetallesCotizacionUi.ESTADO_PROCESO:
                Log.d(TAG, "validarTipoEstadosPropuestaESTADO_PROCESO");
                if (view != null) view.ocultarImagenEliminar();
                break;
            case DetallesCotizacionUi.ESTADO_FINALIZADO:
                Log.d(TAG, "validarTipoEstadosPropuestaESTADO_FINALIZADO");
                if (view != null) view.ocultarImagenEliminar();
                break;
            case DetallesCotizacionUi.ESTADO_PAGADOS:
                if (view != null) view.ocultarImagenEliminar();
                Log.d(TAG, "validarTipoEstadosPropuestaESTADO_REVOCADOS");
                break;
            case DetallesCotizacionUi.ESTADO_REVOCADOS:
                if (view != null) view.ocultarImagenEliminar();
                Log.d(TAG, "validarTipoEstadosPropuestaESTADO_REVOCADOS");
                break;
            default:
                Log.d(TAG, "default");
                break;
        }

    }

    List<CotizacionesUi> cotizacionesUis = new ArrayList<>();


    @Override
    public void onObtenerListaPorEstado(List<CotizacionesUi> cotizacionesUis) {
        //  if (cotizacionesUis == null) return;
        this.cotizacionesUis = cotizacionesUis;
    }

    @Override
    public void onEliminarPropuesta() {
        String mensaje = "Esta seguro que desea eliminar ?";
        if (cotizacionesUis.isEmpty() || cotizacionesUis == null) {
            Log.d(TAG, "Usted no tiene Cotizaciones");
            if (view != null)
                view.mostrarDialogConfirmacion(cotizacionesUis, mensaje, TIPO_ELIMINADO_PROPUESTA_UNICA);
        } else {

            if (view != null)
                view.mostrarDialogConfirmacion(cotizacionesUis, mensaje, TIPO_ELIMINADO_PROPUESTA_COTIZACIONES);
        }
    }

    /*Inicializa mi UseCase Cambiar de Estado propuestaInicial y las cotizaciones */
    @Override
    public void onEliminarPropuestaAceptado(List<CotizacionesUi> cotizacionesUis, String tipoEstado) {
        switch (tipoEstado) {
            case TIPO_ELIMINADO_PROPUESTA_UNICA:
                initEliminarPropuestaUnica(detallesCotizacionUi);
                break;
            case TIPO_ELIMINADO_PROPUESTA_COTIZACIONES:
                initEliminarPropuestaCotizaciones(cotizacionesUis, detallesCotizacionUi);
                break;
        }


    }

    private void initEliminarPropuestaCotizaciones(List<CotizacionesUi> cotizacionesUis, DetallesCotizacionUi detallesCotizacionUi) {
        Log.d(TAG, "onEliminarPropuestaAceptado");
        handler.execute(eliminarPropuesta, new EliminarPropuesta.RequestValues(detallesCotizacionUi, cotizacionesUis),
                new UseCase.UseCaseCallback<EliminarPropuesta.ResponseValue>() {
                    @Override
                    public void onSuccess(EliminarPropuesta.ResponseValue response) {
                        DefaultResponse defaultResponse = response.getDefaultResponse();
                        if (defaultResponse == null) {
                            Log.d(TAG, "Nulo Mensaje");
                            return;
                        }
                        if (response != null) {

                            if (defaultResponse.getError()) {
                                // if (view != null) view.mostrarMensaje(defaultResponse.getMessage());
                                Log.d(TAG, " (defaultResponse.getError())");
                                return;
                            } else {
                                if (view != null) {
                                    //view.mostrarMensaje(defaultResponse.getMessage());
                                    view.initStartActivityMenuCliente(ELIMINADO_CORRECTAMENTE_PROPUESTA);
                                    Log.d(TAG, " else (defaultResponse.getError())");
                                }
                                return;
                            }
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });

    }

    private void initEliminarPropuestaUnica(DetallesCotizacionUi detallesCotizacionUi) {
        Log.d(TAG, "onEliminarPropuestaAceptado");
        handler.execute(eliminarPropuestaUnica, new EliminarPropuestaUnica.RequestValues(detallesCotizacionUi),
                new UseCase.UseCaseCallback<EliminarPropuestaUnica.ResponseValue>() {
                    @Override
                    public void onSuccess(EliminarPropuestaUnica.ResponseValue response) {
                        DefaultResponse defaultResponse = response.getDefaultResponse();
                        if (defaultResponse == null) {
                            Log.d(TAG, "Nulo Mensaje");
                            return;
                        }
                        if (response != null) {

                            if (defaultResponse.getError()) {
                                if (view != null) view.mostrarMensaje(defaultResponse.getMessage());
                                Log.d(TAG, " (defaultResponse.getError())");
                                return;
                            } else {
                                if (view != null) {
                                    //view.mostrarMensaje(defaultResponse.getMessage());
                                    view.initStartActivityMenuCliente(ELIMINADO_CORRECTAMENTE_PROPUESTA);
                                    Log.d(TAG, " else (defaultResponse.getError())");
                                }
                                return;
                            }
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
    }
}
