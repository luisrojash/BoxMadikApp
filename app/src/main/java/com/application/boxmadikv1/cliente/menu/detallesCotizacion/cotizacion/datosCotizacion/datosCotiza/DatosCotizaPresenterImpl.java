package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.DefaultResponseEstados;
import com.application.boxmadikv1.api.response.DefaultResponseEstadosLastId;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.fragment.BaseFragmentPresenterImpl;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.DetallesCotizaFragment;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.useCase.ValidarTipoRevocacion;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;
import com.application.boxmadikv1.utils.Constantes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DatosCotizaPresenterImpl extends BaseFragmentPresenterImpl<DatosCotizaView> implements DatosCotizaPresenter {

    public static final String TAG = DatosCotizaPresenterImpl.class.getSimpleName();
    private ValidarTipoRevocacion validarTipoRevocacion;
    Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);

    public DatosCotizaPresenterImpl(UseCaseHandler handler, Resources res, ValidarTipoRevocacion validarTipoRevocacion) {
        super(handler, res);
        this.validarTipoRevocacion = validarTipoRevocacion;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onActivityCreated() {

    }

    DetallesCotizacionUi detallesCotizacionUi;
    CotizacionesUi cotizacionesUi;

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;
        this.detallesCotizacionUi = extras.getParcelable(Constantes.EXTRAS_CLIENTE_DETALLES_PROPUESTA);
        this.cotizacionesUi = extras.getParcelable(DetallesCotizaFragment.EXTRA_DETALLES_COTIZA_COTIZACIONESUI);
        Log.d(TAG, "DatosPerfilEspecialistaPresenterImpldetallesCotizacionUi : " + detallesCotizacionUi.getNombreProyecto() +
                "DatosPerfilEspecialistaPresenterImpldetallesCotizacionUi : " + cotizacionesUi.getNombreEspecialista());
        mostrarDataInicial();
        //   initValidatePropuestaInicialActiva(detallesCotizacionUi, cotizacionesUi);
    }

    private void mostrarDataInicial() {
        if (cotizacionesUi.getPuntuacion() == null || cotizacionesUi.getPuntuacion().equals("null") || cotizacionesUi.getPuntuacion().isEmpty()) {
            float puntuacion = 0;
            if (view != null) {
                view.mostrarDataInicial(cotizacionesUi.getImagen(), cotizacionesUi.getNombreEspecialista(),
                        puntuacion, cotizacionesUi.getCotiDescripcion(),
                        cotizacionesUi.getCotiPendiente(), cotizacionesUi.getCotiFinalizado(),
                        cotizacionesUi.getCotiAceptado(), cotizacionesUi.getMonto(), cotizacionesUi.getPaisImagen(), cotizacionesUi.getFecha());
            }
        } else {
            float puntuacion = Float.parseFloat(cotizacionesUi.getPuntuacion());
            if (view != null) {
                view.mostrarDataInicial(cotizacionesUi.getImagen(), cotizacionesUi.getNombreEspecialista(),
                        puntuacion, cotizacionesUi.getCotiDescripcion(),
                        cotizacionesUi.getCotiPendiente(), cotizacionesUi.getCotiFinalizado(),
                        cotizacionesUi.getCotiAceptado(), cotizacionesUi.getMonto(), cotizacionesUi.getPaisImagen(), cotizacionesUi.getFecha());
            }
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        onValidaEstadoPropuesta();


    }

    /*Validando para vistas Cotizaciones de boton Aceptar - Desembolsar*/
    private void validarTiposCotizacion(CotizacionesUi cotizacionesUi) {
        Log.d(TAG, " validarTiposCotizacion : " + cotizacionesUi.getEstadoCotizacion());
        switch (cotizacionesUi.getEstadoCotizacion()) {
            case Constantes.ESTADO_ESPECIALISTA_REVOCADOS:
                Log.d(TAG, " ESTADO_ESPECIALISTA_REVOCADOS");
                //initUseCaseValidarTipoRevocacion(Constantes.ESTADO_ESPECIALISTA_REVOCADOS);
                break;
            case Constantes.ESTADO_ESPECIALISTA_PENDIENTE_ENVIADOS:
                Log.d(TAG, " ESTADO_ESPECIALISTA_PENDIENTE_ENVIADOS");
                /*if (view != null) {
                    view.mostrarButtonAceptar();
                    view.ocultarButtonDesembolsar();
                }*/
                if (view != null) {
                    view.mostrarButtonAceptar();
                    view.habilitarButtonAceptarCotiza();

                }
                //initUseCaseValidarTipoRevocacion(Constantes.ESTADO_ESPECIALISTA_PENDIENTE_ENVIADOS);
                break;
            case Constantes.ESTADO_ESPECIALISTA_ACEPTADO:
                Log.d(TAG, " ESTADO_ESPECIALISTA_ACEPTADO");
               /* if (view != null) {
                    view.ocultarButtonAceptar();
                    view.mostrarButtonDesembolsar();
                    //  view.deshabilitarButtonEnviarMensaje();
                    //view.deshabilitarButtonDesembolsar();
                    //view.deshabilitarButtonDesembolsar();
                    //  view.ocultarButtonDesembolsar();

                    //view.mostrarBotonReportePago();

                }*/
               if(view!=null) view.mostrarButtonDesembolsar();

                if (cotizacionesUi.getEstadoCotizacion().equals(Constantes.ESTADO_ESPECIALISTA_ACEPTADO) &&
                        cotizacionesUi.getEstadoPropuesta().equals(Constantes.PROPUESTA_ESTADO_CLIENTE_REVOCADOS)) {
                    if (view != null) {
                        view.mostrarButtonDesembolsar();
                        view.deshabilitarButtonDesembolsar();
                        view.habilitarButtonEnviarMensaje();
                        view.ocultarButtonRevocacionGone();
                        view.deshabilitarButtonAceptarCotiza();
                    }
                    initValidarRespuestaRevocacion();
                    // initUseCaseValidarTipoRevocacion(Constantes.PROPUESTA_ESTADO_CLIENTE_REVOCADOS);
                } else {
                    initUseCaseValidarTipoRevocacion(Constantes.ESTADO_ESPECIALISTA_ACEPTADO);
                }


                break;
            case Constantes.ESTADO_ESPECIALISTA_NO_ACEPTADO:
                Log.d(TAG, " ESTADO_ESPECIALISTA_NO_ACEPTADO");
                //initUseCaseValidarTipoRevocacion(Constantes.ESTADO_ESPECIALISTA_NO_ACEPTADO);
                if (view != null) {
                    view.deshabilitarButtonEnviarMensaje();
                    view.deshabilitarButtonAceptarCotiza();
                }
                break;
            case Constantes.ESTADO_ESPECIALISTA_PAGADO:
                Log.d(TAG, " ESTADO_ESPECIALISTA_PAGADO");
                if (view != null) {
                    view.ocultarButtonAceptar();
                    view.mostrarButtonDesembolsar();
                    view.deshabilitarButtonDesembolsar();
                    //view.habilitarButtonDesembolsar();
                }
                if (cotizacionesUi.getEstadoCotizacion().equals(Constantes.ESTADO_ESPECIALISTA_PAGADO) &&
                        cotizacionesUi.getEstadoPropuesta().equals(Constantes.PROPUESTA_ESTADO_CLIENTE_REVOCADOS)) {
                    initUseCaseValidarTipoRevocacion(Constantes.PROPUESTA_ESTADO_CLIENTE_REVOCADOS);
                    if (view != null) view.habilitarButtonEnviarMensaje();
                    //initUseCaseValidarTipoRevocacion("1");
                    initValidarRespuestaRevocacion();

                }
                break;
            case Constantes.ESTADO_ESPECIALISTA_FINALIZADO:
                Log.d(TAG, " ESTADO_ESPECIALISTA_FINALIZADO");
                if (view != null) {
                    view.ocultarButtonAceptar();
                    view.mostrarButtonDesembolsar();
                    view.deshabilitarButtonDesembolsar();
                }
                break;
            default:
                Log.d(TAG, " ESTADO_ESPECIALISTA_NO_ACEPTADO");
                break;
        }
    }

    private void initUseCaseValidarTipoRevocacion(String tipoEstado) {
        Log.d(TAG, "initUseCaseValidarTipoRevocacion : " + detallesCotizacionUi.getKeyUser());
        String paisCodigo = detallesCotizacionUi.getPaisCodigo();
        handler.execute(validarTipoRevocacion, new ValidarTipoRevocacion.RequestValues(paisCodigo,
                        detallesCotizacionUi.getIdPropuesta(),
                        detallesCotizacionUi.getKeyUser(),
                        tipoEstado),
                new UseCase.UseCaseCallback<ValidarTipoRevocacion.ResponseValue>() {
                    @Override
                    public void onSuccess(ValidarTipoRevocacion.ResponseValue response) {
                        if (response != null) {
                            if (response.getDefaultResponse() == null) {
                                if (view != null) {
                                    Log.d(TAG, " null");
                                    // view.mostrarMensaje(response.getDefaultResponse().getMessage());

                                }
                                return;
                            }
                            if (response.getDefaultResponse().getError()) {
                                /*EXISTE REVOCACION*/
                                if (view != null) {
                                    Log.d(TAG, " EXISTE");
                                    // view.mostrarMensaje(response.getDefaultResponse().getMessage());
                                    if (cotizacionesUi.getEstadoPropuesta().equals(Constantes.PROPUESTA_ESTADO_CLIENTE_FINALIZADO)) {
                                        view.ocultarButtonRevocacionGone();
                                        return;
                                    }
                                    view.mostrarButtonLevantarRevocacion();
                                    view.mostrarButtonVerRepuestaRevocada();
                                    view.habilitarButtonVerRepuestaRevocada();
                                    initValidarRespuestaRevocacion();
                                }
                                return;
                            } else {
                                /*NO EXISTE REVOCACION*/
                                Log.d(TAG, "no EXISTE");
                                if (view != null) {
                                    // view.mostrarMensaje(response.getDefaultResponse().getMessage());
                                    if (cotizacionesUi.getEstadoPropuesta().equals(Constantes.PROPUESTA_ESTADO_CLIENTE_FINALIZADO)) {
                                        view.ocultarButtonRevocacionGone();
                                        return;
                                    }
                                    view.mostrarButtonRevocacion();
                                    // initValidarRespuestaRevocacion();
                                }
                                return;
                            }
                        } else {
                            if (view != null) {
                                //  view.mostrarMensaje("Intentelo de nuevo o compruebe su conexión");
                                Log.d(TAG, "no ERRR");
                            }
                            return;
                        }


                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private void initValidarRespuestaRevocacion() {

        Api apiService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);

        Call<DefaultResponse> call = apiService.validaRespuestaCotizacion(detallesCotizacionUi.getIdPropuesta(),
                detallesCotizacionUi.getUsuarioCodigoPropuesta(),
                cotizacionesUi.getIdUsuarioCotizacion());
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse cambioResponse = response.body();
                if (cambioResponse == null) return;
                if (view != null) {
                    view.mostrarButtonVerRepuestaRevocada();
                    view.mostrarButtonLevantarRevocacion();
                }
                if (cambioResponse.getError()) {
                    /*EXISTE RESPUESTA REVOCADA*/
                    Log.d(TAG, "cambioResponse.getError() : " + cambioResponse.getError());

                    if (view != null) {

                        /******/
                        //view.mostrarButtonVerRepuestaRevocada();
                        //   view.habilitarButtonVerRepuestaRevocada();
                        view.habilitarButtonVerRepuestaRevocada();

                        //****/

                        // view.ocultarButtonRevocacionGone();
                        // view.mostrarButtonLevantarRevocacion();
                    }
                    return;
                } else {
                    /*NO EXISTE RESPUESTA REVOCADA*/
                    Log.d(TAG, "cambioResponse.getError() else: " + cambioResponse.getError());
                    if (view != null) {
                        //view.mostrarButtonVerRepuestaRevocada();
                        // view.ocultarButtonVerRepuestaRevocada();
                        view.deshabilitarButtonVerRepuestaRevocada();
                        //   view.mostrarButtonVerRepuestaRevocada();
                       /* view.ocultarButtonVerRepuestaRevocada();
                        view.mostrarButtonRevocacion();
                        view.deshabilitarButtonVerRepuestaRevocada();*/
                    }
                    return;
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                //   defaultResponseCallBackResultado.onResultado(null);
                Log.d(TAG, "cambioResponse : " + t.getMessage());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (view != null) view.ocultarTeclado();
    }

    @Override
    public void onClickAceptart() {
        //initValidatePropuestaInicialActiva(detallesCotizacionUi, cotizacionesUi);
        switch (cotizacionesUi.getEstadoPropuesta()) {
            case Constantes.PROPUESTA_ESTADO_CLIENTE_PENDIENTE:
                if (view != null) {
                    //view.initStartActivityReportePago(detallesCotizacionUi, cotizacionesUi);
                    view.initStartActivityTipoPago(detallesCotizacionUi,cotizacionesUi);
                }
                break;
        }
    }

    public void onValidaEstadoPropuesta() {
        switch (cotizacionesUi.getEstadoPropuesta()) {
            case Constantes.PROPUESTA_ESTADO_CLIENTE_PENDIENTE:
                if (view != null) {
                    view.habilitarButtonAceptarCotiza();
                    view.habilitarButtonEnviarMensaje();
                    //view.initStartActivityTerminoCondicionesPagar(detallesCotizacionUi, cotizacionesUi);
                    //  view.initStartActivityReportePago(detallesCotizacionUi,cotizacionesUi);
                }
                //validarTiposCotizacion(cotizacionesUi);
                Log.d(TAG, "PROPUESTA_ESTADO_CLIENTE_PENDIENTE");
                break;
            case Constantes.PROPUESTA_ESTADO_CLIENTE_PROCESO:
                Log.d(TAG, "PROPUESTA_ESTADO_CLIENTE_PROCESO");
                /*if (view != null) {
                    view.deshabilitarButtonAceptarCotiza();
                    view.habilitarButtonEnviarMensaje();
                }*/
                validarTiposCotizacion(cotizacionesUi);
                break;
            case Constantes.PROPUESTA_ESTADO_CLIENTE_FINALIZADO:
                if (view != null) {
                    view.deshabilitarButtonAceptarCotiza();
                    view.deshabilitarButtonEnviarMensaje();
                    view.ocultarButtonRevocacionGone();
                }
                validarTiposCotizacion(cotizacionesUi);
                Log.d(TAG, "PROPUESTA_ESTADO_CLIENTE_FINALIZADO");
                break;
            case Constantes.PROPUESTA_ESTADO_CLIENTE_PAGADO:
                if (view != null) {
                    view.deshabilitarButtonAceptarCotiza();
                    view.deshabilitarButtonEnviarMensaje();
                }
                validarTiposCotizacion(cotizacionesUi);
                Log.d(TAG, "ESTADO_ESPECIALISTA_PAGADO");
                break;
            case Constantes.PROPUESTA_ESTADO_CLIENTE_REVOCADOS:
//                if (view != null) {
//                    view.deshabilitarButtonDesembolsar();
//                    view.mostrarButtonDesembolsar();
//                    view.ocultarButtonAceptar();
//                    view.habilitarButtonEnviarMensaje();
//                }
                //validarTiposCotizacion(cotizacionesUi);
                //initUseCaseValidarTipoRevocacion(Constantes.PROPUESTA_ESTADO_CLIENTE_REVOCADOS);
                validarTiposCotizacion(cotizacionesUi);
                Log.d(TAG, "ESTADO_ESPECIALISTA_REVOCADOS");
                break;
        }
    }

    String tipoEstadoPropuesta;

    private void initValidatePropuestaInicialActiva(final DetallesCotizacionUi detallesCotizacionUi, final CotizacionesUi cotizacionesUi) {
        loginService.validaPropuesta(detallesCotizacionUi.getIdPropuesta(),
                detallesCotizacionUi.getPaisCodigo()).enqueue(new Callback<DefaultResponseEstados>() {
            @Override
            public void onResponse(Call<DefaultResponseEstados> call, Response<DefaultResponseEstados> response) {
                DefaultResponseEstados defaultResponseEstados = response.body();
                if (defaultResponseEstados == null) {
                    Log.d(TAG, "defaultResponseEstados==null");
                } else {
                    if (defaultResponseEstados.getError()) {
                        Log.d(TAG, "defaultResponseEstados.getError()");
                    } else {
                        switch (defaultResponseEstados.getEstado()) {
                            case Constantes.PROPUESTA_ESTADO_CLIENTE_PENDIENTE:
                                tipoEstadoPropuesta = defaultResponseEstados.getEstado();
                                // if (view != null) view.initStartActivityTerminoCondicionesPagar(detallesCotizacionUi, cotizacionesUi);
                                break;
                            case Constantes.PROPUESTA_ESTADO_CLIENTE_PROCESO:
                                tipoEstadoPropuesta = defaultResponseEstados.getEstado();

                                break;
                            case Constantes.PROPUESTA_ESTADO_CLIENTE_FINALIZADO:
                                tipoEstadoPropuesta = defaultResponseEstados.getEstado();
                                // if (view != null) view.deshabilitarButtonAceptarCotiza();
                                break;
                            case Constantes.ESTADO_ESPECIALISTA_PAGADO:
                                tipoEstadoPropuesta = defaultResponseEstados.getEstado();
                                // if (view != null) view.deshabilitarButtonAceptarCotiza();
                                break;
                            case Constantes.ESTADO_ESPECIALISTA_REVOCADOS:
                                tipoEstadoPropuesta = defaultResponseEstados.getEstado();
                                //if (view != null) view.deshabilitarButtonAceptarCotiza();
                                break;
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<DefaultResponseEstados> call, Throwable t) {
                Log.d(TAG, "onFailure : " + t.getMessage().toString());
            }
        });
    }

    @Override
    public void onClickEnviarRevocacion() {
        switch (cotizacionesUi.getEstadoCotizacion()) {
            case Constantes.ESTADO_ESPECIALISTA_REVOCADOS:
                String mensajeCancelado = "No se puede - Cotizacion Cancelada";
                if (view != null) view.mostrarMensaje(mensajeCancelado);
                Log.d(TAG, " ESTADO_ESPECIALISTA_REVOCADOS");
                break;
            case Constantes.ESTADO_ESPECIALISTA_PENDIENTE_ENVIADOS:
                Log.d(TAG, " ESTADO_ESPECIALISTA_PENDIENTE_ENVIADOS");
                String mensajePendiente = "No se puede - Cotizacion Pendiente";
                if (view != null) view.mostrarMensaje(mensajePendiente);
                break;
            case Constantes.ESTADO_ESPECIALISTA_ACEPTADO:
                Log.d(TAG, " ESTADO_ESPECIALISTA_ACEPTADO");
                String mensajeProceso = "Cotizacion Disponible ";
                if (view != null)
                    view.initStartActivityTerminoCondicionesRevocacion(detallesCotizacionUi, cotizacionesUi);
                //if(view!=null)view.mostrarMensaje(mensajeProceso);
                break;
            case Constantes.ESTADO_ESPECIALISTA_NO_ACEPTADO:
                Log.d(TAG, " ESTADO_ESPECIALISTA_NO_ACEPTADO");
                break;
            default:
                Log.d(TAG, "  onClickEnviarRevocacion");
                String mensajeDefault = "Aun no aceptado una Cotizacion ";
                if (view != null) view.mostrarMensaje(mensajeDefault);
                break;
        }
    }

    @Override
    public void onClickDesembolsar() {
        switch (cotizacionesUi.getEstadoCotizacion()) {
            case Constantes.ESTADO_ESPECIALISTA_REVOCADOS:
                String mensajeCancelado = "No se puede - Cotizacion Cancelada";
                if (view != null) view.mostrarMensaje(mensajeCancelado);
                Log.d(TAG, " ESTADO_ESPECIALISTA_REVOCADOS");
                break;
            case Constantes.ESTADO_ESPECIALISTA_PENDIENTE_ENVIADOS:
                Log.d(TAG, " ESTADO_ESPECIALISTA_PENDIENTE_ENVIADOS");
                String mensajePendiente = "No se puede - Cotizacion Pendiente";
                if (view != null) view.mostrarMensaje(mensajePendiente);
                break;
            case Constantes.ESTADO_ESPECIALISTA_ACEPTADO:
                Log.d(TAG, " ESTADO_ESPECIALISTA_ACEPTADO");
                String mensajeProceso = "Cotizacion Disponible ";
                if (view != null)
                    view.initStartActivityDesembolsar(detallesCotizacionUi, cotizacionesUi);
                break;
            case Constantes.ESTADO_ESPECIALISTA_PAGADO:
                Log.d(TAG, " ESTADO_ESPECIALISTA_ACEPTADO");

                break;
            case Constantes.ESTADO_ESPECIALISTA_NO_ACEPTADO:
                Log.d(TAG, " ESTADO_ESPECIALISTA_NO_ACEPTADO");
                String mensajeFinalizado = "No se puede - Cotizacion Pendiente";
                if (view != null) view.mostrarMensaje(mensajeFinalizado);
                break;
            default:
                Log.d(TAG, "  onClickEnviarRevocacion");
                String mensajeDefault = "Aun no aceptado una Cotizacion ";
                if (view != null) view.mostrarMensaje(mensajeDefault);
                break;
        }


    }


    @Override
    public void onClickLevantarRevocacion() {
        String mensaje = "Estas seguro que desea levantar la revocación ? ";
        if (view != null)
            view.mostrarDialogConfirmacion(mensaje, detallesCotizacionUi, cotizacionesUi);
        Log.d(TAG, " onClickLevantarRevocacion");
    }


    @Override
    public void onClickMensaje() {

        if (view != null) {
            view.mostrarProgressBar();
        }

        /*initValidarChatExiste(detallesCotizacionUi.getIdPropuesta(),
                detallesCotizacionUi.getPaisCodigo(), detallesCotizacionUi.getUsuarioCodigoPropuesta(), cotizacionesUi.getIdUsuarioCotizacion());

*/
        switch (cotizacionesUi.getEstadoPropuesta()) {
            case Constantes.PROPUESTA_ESTADO_CLIENTE_PENDIENTE:
                Log.d(TAG, "PROPUESTA_ESTADO_CLIENTE_PROCESO");
                if (view != null) {
                    view.deshabilitarButtonAceptarCotiza();
                    view.deshabilitarButtonEnviarMensaje();
                }
                initValidarChatExiste(detallesCotizacionUi.getIdPropuesta(),
                        detallesCotizacionUi.getPaisCodigo(), detallesCotizacionUi.getUsuarioCodigoPropuesta(), cotizacionesUi.getIdUsuarioCotizacion());

                break;

            case Constantes.PROPUESTA_ESTADO_CLIENTE_PROCESO:

                Log.d(TAG, "PROPUESTA_ESTADO_CLIENTE_PROCESO");
                if (view != null) {
                    view.deshabilitarButtonAceptarCotiza();
                    view.deshabilitarButtonEnviarMensaje();
                }
                initValidarChatExiste(detallesCotizacionUi.getIdPropuesta(),
                        detallesCotizacionUi.getPaisCodigo(), detallesCotizacionUi.getUsuarioCodigoPropuesta(), cotizacionesUi.getIdUsuarioCotizacion());
                break;
            case Constantes.PROPUESTA_ESTADO_CLIENTE_REVOCADOS:
                if (cotizacionesUi.getEstadoCotizacion().equals(Constantes.ESTADO_ESPECIALISTA_ACEPTADO) &&
                        cotizacionesUi.getEstadoPropuesta().equals(Constantes.PROPUESTA_ESTADO_CLIENTE_REVOCADOS)) {

                    initValidarChatExiste(detallesCotizacionUi.getIdPropuesta(),
                            detallesCotizacionUi.getPaisCodigo(), detallesCotizacionUi.getUsuarioCodigoPropuesta(), cotizacionesUi.getIdUsuarioCotizacion());
                    return;
                }

                if (view != null) {
                    view.deshabilitarButtonAceptarCotiza();
                    view.deshabilitarButtonEnviarMensaje();
                }
                break;
            default:
                Log.d(TAG, "default");
                if (view != null) {
                    view.deshabilitarButtonAceptarCotiza();
                    view.deshabilitarButtonEnviarMensaje();
                }
                break;

        }


    }

    @Override
    public void onClickReportePago() {
        switch (cotizacionesUi.getEstadoCotizacion()) {
            case Constantes.ESTADO_ESPECIALISTA_ACEPTADO:
                if (view != null) {
                    view.initStartActivityReportePagoPagado(detallesCotizacionUi, cotizacionesUi);
                }
                break;
        }
    }

    @Override
    public void onClickBtnVerRespuesta() {
        if (view != null) {
            view.deshabilitarButtonVerRepuestaRevocada();
            view.initStartActivityRespuestaRevocada(detallesCotizacionUi, cotizacionesUi);
        }
    }

    @Override
    public void onClickCoonfirmarLevantarRevoca(final DetallesCotizacionUi detallesCotizacionUi, final CotizacionesUi cotizacionesUi) {
        if (view != null) {
            String mensaje = "Levantando Revocacion";
            view.mostrarProgressDialog(mensaje);
            view.deshabilitarButtonLevantarRevocacion();
        }
        Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        String pais_codigo = detallesCotizacionUi.getPaisCodigo();
        Call<DefaultResponse> call = loginService.actualizarPropuestaInicial(pais_codigo,
                cotizacionesUi.getIdPropuesta(),
                detallesCotizacionUi.getUsuarioCodigoPropuesta(),
                Constantes.PROPUESTA_ESTADO_CLIENTE_PROCESO);
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        DefaultResponse defaultResponse = response.body();
                        if (defaultResponse.getError()) {
                            if (view != null) {
                                view.mostrarMensaje(defaultResponse.getMessage());
                                view.ocultarProgressDialog();
                                view.deshabilitarButtonLevantarRevocacion();

                            }
                            return;
                        } else {
                            initUseCaseEstadoRevocacionCancelado(detallesCotizacionUi, cotizacionesUi);
                            if (view != null) {
                                view.mostrarMensaje(defaultResponse.getMessage());
                                view.deshabilitarButtonLevantarRevocacion();
                                view.ocultarProgressDialog();
                                view.initStartActivityMenuCliente();
                                //view.ocultarDialogProgress();
                            }
                        }
                    } else {
                        // defaultResponseCallBackResultado.onCallBackResultado(null);
                    }
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                if (view != null) {
                    view.ocultarProgressDialog();
                    view.deshabilitarButtonLevantarRevocacion();
                }
                Log.d(TAG, "onFailureActulizarPropuestaCoti : " + t.getMessage().toString());
            }
        });
    }

    private void initUseCaseEstadoRevocacionCancelado(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi) {
        loginService.actualizarEstadoRevocacion(
                detallesCotizacionUi.getIdPropuesta(),
                detallesCotizacionUi.getUsuarioCodigoPropuesta(),
                cotizacionesUi.getIdUsuarioCotizacion(),
                "51"
        ).enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();
                if (response == null) {
                    String mensaje = "Ocurrio Algun Error";
                    Log.d(TAG, "mensaje : " + mensaje);
                    return;
                }
                if (defaultResponse.getError()) {
                    Log.d(TAG, "mensaje IF: " + defaultResponse.getError());
                } else {
                    Log.d(TAG, "mensaje ELSE : " + defaultResponse.getError());
                }

            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Log.d(TAG, "mensaje onFailure : " + t.getMessage());
            }
        });
    }

    private void initValidarChatExiste(final String idPropuesta, final String paisCodigo, final String usuarioCodigoPropuesta, final String idUsuarioCotizacion) {

        if (view != null) {
            view.deshabilitarButtonEnviarMensaje();
            view.deshabilitarButtonDesembolsar();
        }
        Log.d(TAG,"initValidarChatExiste : "+
                "paisCodigo : "+paisCodigo+ " / "+
                "usuarioCodigoPropuesta : "+usuarioCodigoPropuesta+ " / "+
                "idUsuarioCotizacion : "+idUsuarioCotizacion+ " / ");
        loginService.validarChatGrupoExiste(idPropuesta,
                "51",
                usuarioCodigoPropuesta,
                idUsuarioCotizacion
        ).enqueue(new Callback<DefaultResponseEstadosLastId>() {
            @Override
            public void onResponse(Call<DefaultResponseEstadosLastId> call, Response<DefaultResponseEstadosLastId> response) {
                DefaultResponseEstadosLastId defaultResponse = response.body();
                if (defaultResponse == null) {
                    String mensaje = "Ocurrio Algun Error";
                    Log.d(TAG, "mensaje : " + mensaje);
                    if (view != null) view.habilitarButtonEnviarMensaje();
                    return;
                }
                if (defaultResponse != null) {


                    String tipoEstadoGrupo = defaultResponse.getEstado();
                    String tipoUsuario = "cliente";
                    String idUsuarioCotizacions = idUsuarioCotizacion;
                    String keyUser = detallesCotizacionUi.getKeyUser();
                    String idPropuestas = idPropuesta;
                    String idGrupoChat = defaultResponse.getLastid();
                    String imagenRubro = detallesCotizacionUi.getImageRubro();
                    String nombrePropuesta = detallesCotizacionUi.getNombreProyecto();

                    Log.d(TAG, "tipoEstadoGrupo" + tipoEstadoGrupo +
                            "tipoUsuario" + tipoUsuario +
                            "idUsuarioCotizacions" + idUsuarioCotizacions +
                            "keyUser" + keyUser +
                            "idPropuestas" + idPropuestas +
                            "idGrupoChat" + idGrupoChat +
                            "imagenRubro" + imagenRubro +
                            "nombrePropuesta" + nombrePropuesta
                    );
                    switch (defaultResponse.getEstado()) {
                        case DefaultResponseEstados.RUBRO_USUARIO_EXISTE:
                            Log.d(TAG, "SE REGISTRARON CORRECTAMENTE " + defaultResponse.getLastid());
                            if (view != null) {
                                view.habilitarButtonEnviarMensaje();
                                view.starActivityChat(tipoEstadoGrupo,
                                        idUsuarioCotizacions, keyUser, idPropuestas, idGrupoChat, tipoUsuario, imagenRubro, nombrePropuesta);
                            }
                            break;
                        case DefaultResponseEstados.RUBRO_USUARIO_NO_EXISTE:
                            if (view != null) {
                                view.habilitarButtonEnviarMensaje();
                                view.starActivityChat(tipoEstadoGrupo,
                                        idUsuarioCotizacions, keyUser, idPropuestas, idGrupoChat, tipoUsuario, imagenRubro, nombrePropuesta);
                            }
                            break;
                        case DefaultResponseEstados.CREACION_CORRECTA:
                            /*Crea*/
                            Log.d(TAG, "SE REGISTRARON CORRECTAMENTE " + defaultResponse.getLastid());
                            if (view != null) {
                                view.habilitarButtonEnviarMensaje();
                                view.starActivityChat(tipoEstadoGrupo,
                                        idUsuarioCotizacions, keyUser, idPropuestas, idGrupoChat, tipoUsuario, imagenRubro, nombrePropuesta);
                            }
                            break;
                        case DefaultResponseEstados.CREACION_ERROR:
                            /*Crea*/
                            if (view != null) view.habilitarButtonEnviarMensaje();
                            //if(view!=null)view.mostrarMensaje("Ocurrio algun problema al Guardar!");
                            Log.d(TAG, "CREACION_ERROR");
                            break;
                    }

                }
            }

            @Override
            public void onFailure(Call<DefaultResponseEstadosLastId> call, Throwable t) {
                String mensaje = "Ocurrio Algun Error";
                Log.d(TAG, "mensaje : " + mensaje);
                if (view != null) view.habilitarButtonEnviarMensaje();
                return;
                //Log.d(TAG, "onFailure : " + t.getMessage().toString());
            }
        });

    }


}
