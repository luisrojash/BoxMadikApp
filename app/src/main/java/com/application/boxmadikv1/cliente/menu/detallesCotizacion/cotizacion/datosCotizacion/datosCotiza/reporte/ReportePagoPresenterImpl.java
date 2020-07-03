package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.reporte;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.activity.BaseActivityPresenterImpl;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.DetallesCotizaFragment;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.reporte.useCase.AceptarCotizacion;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.reporte.useCase.NotificacionCotiAceptada;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;
import com.application.boxmadikv1.utils.Constantes;

public class ReportePagoPresenterImpl extends BaseActivityPresenterImpl<ReportePagoView> implements ReportePagoPresenter {
    public static final String CREADO_CORRECTAMENTE_REPORTE_PAGO = "ReportePagoPresenterImpl";

    public static final String TAG = ReportePagoPresenterImpl.class.getSimpleName();
    private AceptarCotizacion aceptarCotizacion;
    private NotificacionCotiAceptada notificacionCotiAceptada;

    public ReportePagoPresenterImpl(UseCaseHandler handler, Resources res, AceptarCotizacion aceptarCotizacion, NotificacionCotiAceptada notificacionCotiAceptada) {
        super(handler, res);
        this.aceptarCotizacion = aceptarCotizacion;
        this.notificacionCotiAceptada = notificacionCotiAceptada;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed");
    }

    DetallesCotizacionUi detallesCotizacionUi;
    CotizacionesUi cotizacionesUi;

    @Override
    public void onStart() {
        super.onStart();

        mostrarDataInicial();
    }

    private void validarEstadosDeCotizaciones() {
        switch (cotizacionesUi.getEstadoCotizacion()) {
            case Constantes.ESTADO_ESPECIALISTA_REVOCADOS:
                String mensajeCancelado = "Esta cotización fue Cancelada";
                if (view != null) view.mostrarMensaje(mensajeCancelado);
                break;
            case Constantes.ESTADO_ESPECIALISTA_PENDIENTE_ENVIADOS:
                Log.d(TAG, " ESTADO_ESPECIALISTA_PENDIENTE_ENVIADOS");
                String mensaje = "";
                if (view != null) view.mostrarDialogoDeConfirmacion(mensaje);
                break;
            case Constantes.ESTADO_ESPECIALISTA_ACEPTADO:
                String mensajeProceso = "Esta cotización fue Aceptada";
                if (view != null) view.mostrarMensaje(mensajeProceso);
                Log.d(TAG, " ESTADO_ESPECIALISTA_ACEPTADO");
                break;
            case Constantes.ESTADO_ESPECIALISTA_NO_ACEPTADO:
                Log.d(TAG, " ESTADO_ESPECIALISTA_NO_ACEPTADO");
                String mensajeFinalizada = "Esta cotización fue Aceptada";
                if (view != null) view.mostrarMensaje(mensajeFinalizada);
                break;
            default:
                break;
        }
    }

    private void mostrarDataInicial() {
        if (view != null) {
            view.mostrarDataInicial(detallesCotizacionUi, cotizacionesUi);
            view.ocultarTeclado();
        }
    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;
        this.detallesCotizacionUi = extras.getParcelable(Constantes.EXTRAS_CLIENTE_DETALLES_PROPUESTA);
        this.cotizacionesUi = extras.getParcelable(DetallesCotizaFragment.EXTRA_DETALLES_COTIZA_COTIZACIONESUI);
        Log.d(TAG, "detallesCotizacionUi.getImageRubro() : " + detallesCotizacionUi.getImageRubro() +
                "detallesCotizacionUi.getNombreProyecto() : " + detallesCotizacionUi.getNombreProyecto() +
                "detallesCotizacionUi.getDetallesPropuesta() : " + detallesCotizacionUi.getDetallesPropuesta() +
                "cotizacionesUi.getNombreEspecialista() : " + cotizacionesUi.getNombreEspecialista() +
                "getIdUsuarioCotizacion : " + cotizacionesUi.getIdUsuarioCotizacion());
        String imageViewRubro = detallesCotizacionUi.getImageRubro();
        String nombrePropuesta = detallesCotizacionUi.getNombreProyecto();
        String detallePropuesta = detallesCotizacionUi.getDetallesPropuesta();
        String fechaPropuesta = detallesCotizacionUi.getFechaPropuesta();
        String nombreEspecialista = cotizacionesUi.getNombreEspecialista();
        String imagenEspecialista = cotizacionesUi.getImagen();
        String fechaEspecialista = cotizacionesUi.getFecha();
       /* if (view != null) {
            view.mostrarDataInicial(imageViewRubro, nombrePropuesta,detallePropuesta,fechaPropuesta,nombreEspecialista,nombreEspecialista,imagenEspecialista,fechaEspecialista);
        }*/


    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onClickBtnFinalizar() {
       //initEnvioNotificacionAceptadoCotizacion(); --- Envia Notificacion de Cotizacion
        if (view != null) {
            //  view.mostrarMensaje(defaultResponse.getMessage());
            view.initStartActivityMenuCliente(CREADO_CORRECTAMENTE_REPORTE_PAGO);
        }


        // onClickAceptoReportePago();
        // validarEstadosDeCotizaciones();

    }

    @Override
    public void onClickAceptoReportePago() {
        handler.execute(aceptarCotizacion, new AceptarCotizacion.RequestValues(detallesCotizacionUi, cotizacionesUi),
                new UseCase.UseCaseCallback<AceptarCotizacion.ResponseValue>() {
                    @Override
                    public void onSuccess(AceptarCotizacion.ResponseValue response) {
                        DefaultResponse defaultResponse = response.getDefaultResponse();

                        if (response != null) {
                            if (defaultResponse.getError() == null) {
                                Log.d(TAG, "Problemas con los servidores");
                                return;
                            }
                            if (defaultResponse.getError()) {
                                if (view != null) view.mostrarMensaje(defaultResponse.getMessage());
                                return;
                            } else {
                                if (defaultResponse == null) {
                                    Log.d(TAG, "Problemas con los servidores");
                                    return;
                                }
                                //initEnvioNotificacionAceptadoCotizacion();
                                if (view != null) {
                                    //  view.mostrarMensaje(defaultResponse.getMessage());
                                    view.initStartActivityMenuCliente(CREADO_CORRECTAMENTE_REPORTE_PAGO);
                                }
                                return;

                            }
                        } else {
                            Log.d(TAG, "Problemas con los servidores");
                            return;
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    @Override
    public void onBackPresseDatosCotiza() {
//        if(view!=null)view.initStartActivityDatosCotiza(detallesCotizacionUi,cotizacionesUi);
        if(view!=null)view.initStartActivityMenuCliente(CREADO_CORRECTAMENTE_REPORTE_PAGO);
    }

    private void initEnvioNotificacionAceptadoCotizacion() {
        handler.execute(notificacionCotiAceptada, new NotificacionCotiAceptada.RequestValues(
                        Constantes.NOTIFICACION_NOT_ESTADO_PENDIENTE,
                        Constantes.TIPO_NOTIFICACION_ACEPTO_COTIZACION,
                        Constantes.GRUPO_NOTIFICACION_CLIENTE,
                        detallesCotizacionUi.getIdPropuesta(),
                        cotizacionesUi.getIdCotizacion(),
                        detallesCotizacionUi.getPaisCodigo()
                ),
                new UseCase.UseCaseCallback<NotificacionCotiAceptada.ResponseValue>() {
                    @Override
                    public void onSuccess(NotificacionCotiAceptada.ResponseValue response) {

                    }

                    @Override
                    public void onError() {

                    }
                });
    }
}
