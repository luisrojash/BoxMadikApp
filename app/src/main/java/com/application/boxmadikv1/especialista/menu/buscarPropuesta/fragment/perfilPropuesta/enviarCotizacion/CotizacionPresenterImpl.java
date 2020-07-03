package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.enviarCotizacion;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;

import com.application.boxmadikv1.api.response.especialista.DatosCotizacionResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.base.fragment.BaseFragmentPresenterImpl;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.ItemUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.enviarCotizacion.useCase.EnviarCotizacion;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.enviarCotizacion.useCase.EnvioNotificacion;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.enviarCotizacion.useCase.ObtenerComision;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.enviarCotizacion.useCase.ObtenerTipoCambioDolar;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.enviarCotizacion.useCase.ObtenerValidacionCotizacion;
import com.application.boxmadikv1.utils.Constantes;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class CotizacionPresenterImpl extends BaseFragmentPresenterImpl<CotizacionView> implements CotizacionPresenter {

    public static final String TAG = CotizacionPresenterImpl.class.getSimpleName();
    private ObtenerComision obtenerComision;
    private ObtenerTipoCambioDolar obtenerTipoCambioDolar;
    private EnviarCotizacion enviarCotizacion;
    private ObtenerValidacionCotizacion obtenerValidacionCotizacion;
    private EnvioNotificacion envioNotificacion;

    public CotizacionPresenterImpl(UseCaseHandler handler, Resources res, ObtenerComision obtenerComision, ObtenerTipoCambioDolar obtenerTipoCambioDolar, EnviarCotizacion enviarCotizacion, ObtenerValidacionCotizacion obtenerValidacionCotizacion, EnvioNotificacion envioNotificacion) {
        super(handler, res);
        this.obtenerComision = obtenerComision;
        this.obtenerTipoCambioDolar = obtenerTipoCambioDolar;
        this.enviarCotizacion = enviarCotizacion;
        this.obtenerValidacionCotizacion = obtenerValidacionCotizacion;
        this.envioNotificacion = envioNotificacion;
    }


    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onActivityCreated() {

    }

    @Override
    public void onClickFechaInicio() {
        if (view != null) view.mostrarDialogFechaInicio();
    }

    @Override
    public void onClickFechaFin() {
        if (view != null) view.mostrarDialogFechaFin();

    }

    @Override
    public void onStart() {
        super.onStart();
        initVistasInicial(itemUi);
    }

    private void initiValidateTipoEstados() {
        //  initUseCaseValidacionCotizacion(Constantes.PROPUESTA_ESTADO_CLIENTE_PENDIENTE);
        /*if (itemUi.getCotiEstado() == null) {
            Log.d(TAG, "COTIESTADO NULL");
            obtenerCotizacionBol = false;
            return;
        }*/
        switch (itemUi.getPropuestaEstado()) {
            case Constantes.PROPUESTA_ESTADO_CLIENTE_PENDIENTE:
                Log.d(TAG, "PROPUESTA_ESTADO_CLIENTE_PENDIENTE");
                initUseCaseValidacionCotizacion(Constantes.PROPUESTA_ESTADO_CLIENTE_PENDIENTE);
                break;
            case Constantes.PROPUESTA_ESTADO_CLIENTE_PROCESO:

                Log.d(TAG, "COTIESTADO PROPUESTA_ESTADO_CLIENTE_PROCESO" + itemUi.getCotiEstado());
                // initUseCaseValidacionCotizacion(Constantes.PROPUESTA_ESTADO_CLIENTE_PROCESO);
                initValidarCotiza();

                Log.d(TAG, "PROPUESTA_ESTADO_CLIENTE_PROCESO");
                break;
            case Constantes.PROPUESTA_ESTADO_CLIENTE_FINALIZADO:
                Log.d(TAG, "PROPUESTA_ESTADO_CLIENTE_FINALIZADO");
                Log.d(TAG, "COTIESTADO PROPUESTA_ESTADO_CLIENTE_FINALIZADO" + itemUi.getCotiEstado());
                //initUseCaseValidacionCotizacion(Constantes.PROPUESTA_ESTADO_CLIENTE_FINALIZADO);
                initValidarCotiza();
                break;
            case Constantes.PROPUESTA_ESTADO_CLIENTE_PAGADO:
                Log.d(TAG, "PROPUESTA_ESTADO_CLIENTE_PAGADO");
                Log.d(TAG, "COTIESTADO PROPUESTA_ESTADO_CLIENTE_PAGADO" + itemUi.getCotiEstado());
                initValidarCotiza();
                //initUseCaseValidacionCotizacion(Constantes.PROPUESTA_ESTADO_CLIENTE_PAGADO);
                break;
            case Constantes.PROPUESTA_ESTADO_CLIENTE_REVOCADOS:
                Log.d(TAG, "PROPUESTA_ESTADO_CLIENTE_REVOCADOS");
                // initValidarCotiza();
                initUseCaseValidacionCotizacion(Constantes.ESTADO_ESPECIALISTA_ACEPTADO);
                break;
            default:
                initUseCaseValidacionCotizacion("");
                break;
        }

    }

    private void initValidarCotiza() {
        Log.d(TAG, "itemUi.getCotiEstado() : " + itemUi.getCotiEstado());
        switch (itemUi.getCotiEstado()) {
            case Constantes.ESTADO_ESPECIALISTA_ACEPTADO:
                Log.d(TAG, "ESTADO_ESPECIALISTA_PAGADO");
                initUseCaseValidacionCotizacion(Constantes.ESTADO_ESPECIALISTA_ACEPTADO);
                break;
            case Constantes.ESTADO_ESPECIALISTA_NO_ACEPTADO:
                Log.d(TAG, "ESTADO_ESPECIALISTA_PAGADO");
                initUseCaseValidacionCotizacion(Constantes.ESTADO_ESPECIALISTA_NO_ACEPTADO);
                break;
            case Constantes.ESTADO_ESPECIALISTA_PAGADO:
                Log.d(TAG, "ESTADO_ESPECIALISTA_PAGADO");
                initUseCaseValidacionCotizacion(Constantes.ESTADO_ESPECIALISTA_PAGADO);
                break;
            case Constantes.ESTADO_ESPECIALISTA_FINALIZADO:
                Log.d(TAG, "ESTADO_ESPECIALISTA_FINALIZADO");
                initUseCaseValidacionCotizacion(Constantes.ESTADO_ESPECIALISTA_FINALIZADO);
                break;

        }
    }

    boolean obtenerCotizacionBol;

    private void initUseCaseValidacionCotizacion(String tipoEstado) {
        // String paisCodigo = "51";

        Log.d(TAG, "itemUi.getKeyUser() : " + itemUi.getKeyUser() +
                "itemUi.getCodigoPropuesta() : " + itemUi.getCodigoPropuesta() +
                "tipoEstado " + tipoEstado +
                " itemUi.getPaisCodigo() " + itemUi.getPaisCodigo());
        if (estadoInternet) {
            //initUseCaseValidacionCotizazacion(tipoEstado);
            handler.execute(obtenerValidacionCotizacion, new ObtenerValidacionCotizacion.RequestValues(
                    itemUi.getKeyUser(),
                    itemUi.getCodigoPropuesta(),
                    tipoEstado,
                    "51"
            ), new UseCase.UseCaseCallback<ObtenerValidacionCotizacion.ResponseValue>() {
                @Override
                public void onSuccess(ObtenerValidacionCotizacion.ResponseValue response) {
                    if (response == null) return;
                    if (response != null) {
                        DatosCotizacionResponse datosCotizacionResponse = response.getDatosCotizacionEspecialistaResponse();
                        if (datosCotizacionResponse != null) {
                            /*Cuando es True es porque hay Error*/
                            if (datosCotizacionResponse.getError()) {
                                Log.d(TAG, "datosCotizacionResponse.getError()");
                                obtenerCotizacionBol = false;
                                if (view != null) {
                                    //view.mostrarMensaje("No tiene ninguna Cotización");
                                    view.ocultarProgressBar();
                                }
                                return;
                            } else {
                                Log.d(TAG, "datosCotizacionResponse.TRUE()");
                                obtenerCotizacionBol = true;
                                DatosCotizacionResponse.DatosCotizacionEspecialistaResponse datosCotizacionEspecialistaResponse = datosCotizacionResponse.getDatosCotizacionEspecialistaResponse();
                                if (datosCotizacionEspecialistaResponse != null) {
                                    String montoNeto = datosCotizacionEspecialistaResponse.getCoti_Monto_Ini();
                                    String montoComision = datosCotizacionEspecialistaResponse.getCoti_Monto_Comi();
                                    String totalSoles = datosCotizacionEspecialistaResponse.getCoti_Monto_Total();
                                    String totalDolares = datosCotizacionEspecialistaResponse.getCoti_Monto_Dolar();
                                    String fechaInicio = Constantes.recortarFechaRealizo(datosCotizacionEspecialistaResponse.getCoti_FecIni());
                                    if (fechaInicio == null) return;
                                    String fechaFin = Constantes.recortarFechaRealizo(datosCotizacionEspecialistaResponse.getCoti_FecFin());
                                    String descripcion = datosCotizacionEspecialistaResponse.getCoti_Descripcion();
                                    if (view != null) {
                                        // view.mostrarMensaje(response.getDatosCotizacionEspecialistaResponse().getMensaje());
                                        view.ocultarProgressBar();
                                        view.mostrarCamposLlenos(montoNeto, montoComision, totalSoles, totalDolares, fechaInicio, fechaFin, descripcion);
                                    }
                                    return;
                                } else {
                                    Log.d(TAG, "datosCotizacionResponse.elseelse()");
                                    obtenerCotizacionBol = false;
                                    if (view != null) {
                                        //  view.mostrarMensaje("Problemas con nuestros servidores2");
                                        view.ocultarProgressBar();
                                    }
                                    return;
                                }

                            }
                        } else {
                            Log.d(TAG, "datosCotizacionResponse.else");
                            obtenerCotizacionBol = false;
                            if (view != null) {
                                //  view.mostrarMensaje("Problemas con nuestros servidores");
                                view.ocultarProgressBar();
                            }
                            return;
                        }
                    }
                }

                @Override
                public void onError() {
                    obtenerCotizacionBol = false;
                }
            });
        } else {
            if (view != null)
                view.mostrarDialogMensaje("No se pudo conectar a Internet. Desea Intentar denuevo?");
        }
    }

    private void initUseCaseValidacionCotizazacion(String tipoEstado) {


    }

    String dateInicio = null, dateFin = null;

    @Override
    public void onAceptarDateInicio(String dateTime) {
        this.dateInicio = dateTime;
        Log.d(TAG, "onAceptarDateInicio : " + dateTime);
    }

    @Override
    public void onAceptarDateFin(String dateTime) {
        this.dateFin = dateTime;
        Log.d(TAG, "onAceptarDateFin : " + dateTime);
    }

    String onTextChanged;
    double montoNetoStr;

    @Override
    public void onTextChanged(Editable s) {
        if (obtenerCotizacionBol) {
            Log.d(TAG, " NO HACER NADA PORQUE SI TIENE COTIZACION ;  ");
        } else {
            onTextChanged = s.toString();
            Log.d(TAG, " onTextChanged ;  " + onTextChanged);

            if (s.length() == 0) {
                if (view != null) {
                    view.editTextComision("0");
                    view.editTextTotalSoles("0");
                    view.ediTextTotalDolares("0");
                }
                //if (view != null) view.editTextComision("0");
                // if (view != null) view.limpiarTexto();
                Log.d(TAG, " Data000 ;  " + s);
            } else {
                //double aDouble =
                //double montoNeto = Double.parseDouble(s.toString());
                try {
                    montoNetoStr = Double.parseDouble(s.toString());
                    Log.d(TAG, "Enviar Menu Principal : " + montoNetoStr);
                    initUseCaseObtenerComision(montoNetoStr);
                } catch (Exception e) {
                    Log.d(TAG, "Enviar Menu Principal");
                }

            }
        }


    }

    private void initRegistrarOficiDefecto(String descripcion) {
        if (onTextChanged == null) {
            if (view != null) {
                view.mostrarMensajeEditTextMontoError("No se permiten campos Vacios,en Montos");
                view.habilitarButtonCotizacion();
            }
            Log.d(TAG, "No se permiten campos Vacios,en Montos");
            return;
        }
        if (onTextChanged.equals("") || onTextChanged.isEmpty() || onTextChanged == null) {//|| onTextChanged.equals("0")
            if (view != null) {
                view.mostrarMensajeEditTextMontoError("No se permiten campos Vacios,en Montos");
                view.habilitarButtonCotizacion();
            }
            Log.d(TAG, "No se permiten campos Vacios");
            return;
        }


        if (dateInicio == null || dateInicio.isEmpty() || dateInicio.equals("Fecha Inicio")) {
            if (view != null) {
                view.mostrarMensaje("Elija la Fecha Inicial");
                view.habilitarButtonCotizacion();
            }
            Log.d(TAG, "dateInicio.isEmpty()");
            return;
        }
        if (dateFin == null || dateFin.isEmpty() || dateFin.equals("Fecha Fin")) {
            if (view != null) {
                view.mostrarMensaje("Elija la Fecha Final");
                view.habilitarButtonCotizacion();
            }
            Log.d(TAG, "dateInicio.isEmpty()");
            return;

        }
        if (!Constantes.CheckDates(Constantes.recortarFechaRealizo(itemUi.getFechaPropuesta()), dateInicio)) {
            if (view != null) {
                view.mostrarMensaje("La fecha Inicio tiene que se mayor o igual de la propuesta");
                view.habilitarButtonCotizacion();
            }
            return;

        }
        if (!Constantes.CheckDates(dateInicio, dateFin)) {
            if (view != null) {
                view.mostrarMensaje("La Fecha Fin tiene que se mayor o igual de la Fecha Inicio ");
                view.habilitarButtonCotizacion();
            }
            return;

        }
        if (descripcion.isEmpty() || descripcion.equals("") || descripcion == null) {
            if (view != null) {
                view.mostrarMensajeEditTextDescripcionError("No se permiten campos Vacios o escriba correcatamente");
                Log.d(TAG, "No se permiten campos Vacios ,Llene descripcion");
                view.habilitarButtonCotizacion();
                return;
            }

        }
        Log.d(TAG, "coti_MontoNeto : " + coti_MontoNeto +
                "/ coti_SumaTotalSoles : " + coti_SumaTotalSoles +
                " / onTextChanged : " + onTextChanged);

        double montoCotizado = Double.parseDouble(onTextChanged);


        if (montoCotizado < 40) {
            if (view != null)
                view.mostrarMensajeEditTextMontoError("El monto tiene que ser mayor a 40");
            view.habilitarButtonCotizacion();
            return;
        }
        if (montoCotizado >= 100000) {//1000000000
            if (view != null) {
                view.mostrarMensaje("El monto cotizado tiene que ser menor a cien mil");
                view.habilitarButtonCotizacion();
            }
            return;
        }
        if (coti_SumaTotalSoles >= 100000) {//1000000000
            if (view != null) {
                view.mostrarMensaje("El total tiene que ser menor a cien mil");
                view.habilitarButtonCotizacion();
            }
            return;
        }


        String removeAcentosCadena = Constantes.removeAcentos(descripcion);
        String isPrimeroResultadoCharacter = Constantes.isPrimeroResultadoCharacter(removeAcentosCadena);
        String isSegundoresultadoCharacter = Constantes.isSegundoresultadoCharacter(isPrimeroResultadoCharacter);


        initUseCaseEnviarCotizacion(isSegundoresultadoCharacter,
                montoCotizado,
                coti_ComisionTotal,
                coti_SumaTotalSoles,
                coti_SumaTotalDolares,
                monedaDolar);
    }

    private void initRegistroOficioPersonal(final String descripcion) {

        initRegistrarOficOfci(montoNetoOfi, totalComicionOfi, totalSolesOfi, totalDolares, descripcion);
    }

    private void initRegistrarOficOfci(double montoNeto, double totalComision, double totalSoles, double totalDolares, String descripcion) {

        if (dateInicio == null || dateInicio.isEmpty() || dateInicio.equals("Fecha Inicio")) {
            if (view != null) {
                view.mostrarMensaje("Elija la Fecha Inicial");
                view.habilitarButtonCotizacion();
            }
            Log.d(TAG, "dateInicio.isEmpty()");
            return;
        }
        if (dateFin == null || dateFin.isEmpty() || dateFin.equals("Fecha Fin")) {
            if (view != null) {
                view.mostrarMensaje("Elija la Fecha Final");
                view.habilitarButtonCotizacion();
            }
            Log.d(TAG, "dateInicio.isEmpty()");
            return;

        }
        if (!Constantes.CheckDates(Constantes.recortarFechaRealizo(itemUi.getFechaPropuesta()), dateInicio)) {
            if (view != null) {
                view.mostrarMensaje("La fecha Inicio tiene que se mayor o igual de la propuesta");
                view.habilitarButtonCotizacion();
            }
            return;

        }
        if (!Constantes.CheckDates(dateInicio, dateFin)) {
            if (view != null) {
                view.mostrarMensaje("La Fecha Fin tiene que se mayor o igual de la Fecha Inicio ");
                view.habilitarButtonCotizacion();
            }
            return;

        }
        if (descripcion.isEmpty() || descripcion.equals("") || descripcion == null) {
            if (view != null) {
                view.mostrarMensajeEditTextDescripcionError("No se permiten campos Vacios o escriba correcatamente");
                Log.d(TAG, "No se permiten campos Vacios ,Llene descripcion");
                view.habilitarButtonCotizacion();
                return;
            }

        }
        String otros = "otros";
        // String validateDescripcion = Constantes.isResultadoEspecial(descripcion);

        String removeAcentosCadena = Constantes.removeAcentos(descripcion);
        String isPrimeroResultadoCharacter = Constantes.isPrimeroResultadoCharacter(removeAcentosCadena);
        String isSegundoresultadoCharacter = Constantes.isSegundoresultadoCharacter(isPrimeroResultadoCharacter);

        Log.d(TAG, "montoNeto :" + montoNeto +
                "totalComision :" + totalComision +
                "montoNeto :" + montoNeto +
                "totalDolares: " + totalDolares +
                "monedaDolarOfi: " + monedaDolarOfi);
        boxmadik_comision = "0.0";

        initUseCaseEnviarCotizacion(isSegundoresultadoCharacter,
                montoNeto,
                Double.parseDouble(boxmadik_comision),
                montoNeto,
                totalDolares,
                monedaDolarOfi);

    }

    @Override
    public void onEnviarCotizacion(String descripcion) {
        Log.d(TAG, "onEnviarCotizacion: " + descripcion);
        if (view != null) view.deshabilitarButtonCotizacion();
        switch (tipoOficioPersonal) {
            case "oficioPer":
                initRegistroOficioPersonal(descripcion);
                break;
            case "oficioNormal":
                initRegistrarOficiDefecto(descripcion);
                break;
            default:
                break;
        }


    }
    //double totalDolares = Constantes.fijarNumero((montoNeto + totalComision) / monedaDolar, 3);

    Boolean estadoInternet;

    @Override
    public void onCheckConexion(Boolean internet) {
        if (internet) {
            this.estadoInternet = internet;
            Log.d(TAG, "CONECTADO A INTERNET");
            //validarDatosInicialºes();
            initiValidateTipoEstados();
        } else {
            this.estadoInternet = internet;
            Log.d(TAG, "DESCONECTADO A INTERNET");
            if (view != null)
                view.mostrarDialogMensaje("No se pudo conectar a Internet. Desea Intentar denuevo?");
        }
    }

    private void initUseCaseEnviarCotizacion(String descripcion, double coti_montoNeto, double coti_comisionTotal, double coti_sumaTotalSoles, double coti_sumaTotalDolares, double monedaDolar) {
       /* if(coti_montoNeto<30){
            view.mostrarMensajeEditTextMontoError("El monto tiene que ser mayor a 30");
            return;
        }
        if (coti_montoNeto>1000000000){
            view.mostrarMensajeEditTextMontoError("El monto tiene que ser mayor a 1 millón");
            return;
        }*/
        if (view != null) view.mostrarDialogProgressBar();

        Log.d(TAG, "coti_montoNeto :" + coti_montoNeto +
                "coti_comisionTotal :" + coti_comisionTotal +
                "coti_sumaTotalSoles :" + coti_sumaTotalSoles +
                "coti_sumaTotalDolares: " + coti_sumaTotalDolares +
                "itemUi.getKeyUser(): " + itemUi.getKeyUser() +
                "itemUi.getKeyUser(): " + itemUi.getKeyUser() +
                "itemUi.getKeyUser(): " + itemUi.getKeyUser() +
                "dateInicio: " + dateInicio +
                "dateFin: " + dateFin +
                "boxmadik_comision: " + boxmadik_comision +
                "descripcion: " + descripcion
        );

        handler.execute(enviarCotizacion, new EnviarCotizacion.RequestValues(descripcion,
                        coti_montoNeto,
                        coti_comisionTotal,
                        coti_sumaTotalSoles,
                        coti_sumaTotalDolares,
                        itemUi.getKeyUser(),
                        monedaDolar,
                        itemUi.getCodigoPropuesta(),
                        dateInicio,
                        dateFin,
                        boxmadik_comision
                ),
                new UseCase.UseCaseCallback<EnviarCotizacion.ResponseValue>() {
                    @Override
                    public void onSuccess(EnviarCotizacion.ResponseValue response) {
                        if (response.getDefaultResponse() == null) return;
                          /*   if (view != null)
                            view.mostrarMensaje(response.getDefaultResponse().getMessage());*/
                        if (response != null) {
                            if (response.getDefaultResponse() == null) {
                                if (view != null) {
                                    view.mostrarMensaje(response.getDefaultResponse().getMessage());
                                    view.ocultarDialogProgressBar();
                                    view.habilitarButtonCotizacion();
                                    //view.limpiarTexto();
                                    Log.d(TAG, "response.getDefaultResponse() == null");
                                    //   view.startMenuClienteActivity(CREADO_CORRECTAMENTE_PROPUESTA);
                                }
                                return;
                            }
                            if (response.getDefaultResponse().getError()) {
                                if (view != null) {
                                    view.mostrarMensaje(response.getDefaultResponse().getMessage());
                                    view.ocultarDialogProgressBar();
                                    view.habilitarButtonCotizacion();
                                    Log.d(TAG, "response.getDefaultResponse().getError()");
                                }
                                return;
                            } else {
//                                if (view != null) {
//
//                                    view.mostrarMensaje("Cotización enviada Correctamente!!");
//                                    view.ocultarDialogProgressBar();
//                                    view.limpiarTexto();
//                                    view.initStartActivityEspec();
//                                    Log.d(TAG, "else response.getDefaultResponse().getError()");
//
//                                }
                                validarEnvioCorrectaView();
                                String codigoCotizacion = response.getDefaultResponse().getLastid();
                                Log.d(TAG, "codigoCotizacion : " + codigoCotizacion);
                                initServiceEnvioCotizacionNotificacion(itemUi, codigoCotizacion);
                                return;
                            }
                        } else {
                            if (view != null) {
                                view.mostrarMensaje("Intentelo de nuevo o compruebe su conexión");
                                view.ocultarDialogProgressBar();
                                view.habilitarButtonCotizacion();
                            }
                            return;
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });


    }

    private void validarEnvioCorrectaView() {

        switch (tipoLlegadaExtra) {
            case "notificacionClicked": /*Aqui Cuando llega de notificaciones de Segundo Plano*/
                Log.d(TAG, "notificacionClicked::default");
                if (view != null) {
                    view.mostrarMensaje("Cotización enviada Correctamente!!");
                    view.ocultarDialogProgressBar();
                    view.limpiarTexto();
                    view.initStartActivityEspec();
                    Log.d(TAG, "else response.getDefaultResponse().getError()");
                }
                break;
            case "actividadClicked":
                Log.d(TAG, "actividadClicked::default");
                if (view != null) {
                    view.mostrarMensaje("Cotización enviada Correctamente!!");
                    view.ocultarDialogProgressBar();
                    view.limpiarTexto();
                    view.initStartActivityEspec();
                    Log.d(TAG, "else response.getDefaultResponse().getError()");

                }
                break;
            case "actividadNotiNoti":/*Aqui cuando llega de Seleccionar Usuario*/
                Log.d(TAG, "actividadNotiNoti::default");
                if (view != null) {
                    view.mostrarMensaje("Cotización enviada Correctamente!!");
                    view.ocultarDialogProgressBar();
                    view.limpiarTexto();
                    view.initStartActivitySeleccionarUser();
                }
                break;
            default:
                Log.d(TAG, "validarEnvioCorrectaView::default");
                break;
        }


    }

    private void initServiceEnvioCotizacionNotificacion(ItemUi itemUi, String codigoCotizacion) {
        // String paisCodigo = "51";
        Log.d(TAG, "itemUi.getKeyUser()" + itemUi.getKeyUser() +
                "itemUi.getCodigoPropuesta()" + itemUi.getCodigoPropuesta() +
                " itemUi.getPaisCodigo()" + itemUi.getPaisCodigo() +
                " codigoCotizacion : " + codigoCotizacion);
        handler.execute(envioNotificacion,
                new EnvioNotificacion.RequestValues(Constantes.GRUPO_NOTIFICACION_ESPECIALISTA,
                        Constantes.TIPO_NOTIFICACION_CREACION_COTIZACION,
                        itemUi.getKeyUser(),
                        itemUi.getCodigoPropuesta(),
                        itemUi.getPaisCodigo(),
                        codigoCotizacion), new UseCase.UseCaseCallback<EnvioNotificacion.ResponseValue>() {
                    @Override
                    public void onSuccess(EnvioNotificacion.ResponseValue response) {

                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    double coti_MontoNeto, coti_ComisionTotal, coti_SumaTotalSoles, coti_SumaTotalDolares;

    String boxmadik_comision;

    private void initUseCaseObtenerComision(final double monto) {
        /*if (monto < 40) {
            if (view != null)
                view.mostrarMensajeEditTextMontoError("El monto tiene que ser mayor a 40");
        }*/

        //String countMonto = String.valueOf(monto);
      /*  if (countMonto.length() > 9) {
            if (view != null)
                view.mostrarMensajeEditTextMontoError("Solo puede colocar menor a 9 numeros");
            return;
        }*/

      /*  if (monto >= 100000) {//1000000000
            if (view != null)
                view.mostrarMensajeEditTextMontoError("El monto tiene que ser menor a cien mil");
        }*/

        handler.execute(obtenerComision, new ObtenerComision.RequestValues(),
                new UseCase.UseCaseCallback<ObtenerComision.ResponseValue>() {
                    @Override
                    public void onSuccess(ObtenerComision.ResponseValue response) {
                        if (response.getObtenerComisionPorcentaje() == null) return;
                        boxmadik_comision = response.getObtenerComisionPorcentaje();
                        double porcentajecomision = Double.parseDouble(response.getObtenerComisionPorcentaje());
                        //double montoNeto = Double.parseDouble(monto);
                        /*casodeusoInternetconapi*/
                        initUseCaseTipoCambioDolares(monto, porcentajecomision);
                        Log.d(TAG, "obtenerComision : " + porcentajecomision);

                    }

                    @Override
                    public void onError() {

                    }
                });


    }

    double monedaDolar;

    private void initUseCaseTipoCambioDolares(final double montoNeto, final double porcentajecomision) {
        Log.d(TAG, "COMISION RESULTAOD" + montoNeto);
        handler.execute(obtenerTipoCambioDolar, new ObtenerTipoCambioDolar.RequestValues(itemUi.getPaisCodigo()),
                new UseCase.UseCaseCallback<ObtenerTipoCambioDolar.ResponseValue>() {
                    @Override
                    public void onSuccess(ObtenerTipoCambioDolar.ResponseValue response) {

                        if (response == null) return;
                        if (response.getTipoCambio() == null) return;
                        double dolarMonedas = Double.parseDouble(response.getTipoCambio());
                        monedaDolar = Constantes.fijarNumero(dolarMonedas, 2);
                        double totalComision = Constantes.fijarNumero(((montoNeto * porcentajecomision) / 100), 2);
                        double totalSoles = Constantes.fijarNumero(montoNeto + totalComision, 2);
                        double totalDolares = Constantes.fijarNumero((montoNeto + totalComision) / monedaDolar, 2);
                        Log.d(TAG, "totalComision: " + totalComision
                                + " totalSoles : " + totalSoles
                                + " totalDolares " + totalDolares);

                        coti_MontoNeto = montoNeto;
                        coti_ComisionTotal = totalComision;
                        coti_SumaTotalSoles = totalSoles;
                        coti_SumaTotalDolares = totalDolares;
                        if (view != null) {
                            view.editTextComision("" + totalComision + "");
                            view.editTextTotalSoles("" + totalSoles + "");
                            view.ediTextTotalDolares("" + totalDolares + "");
                        }

                        /*if (coti_SumaTotalSoles > 100000) {//1000000000
                            if (view != null)
                                view.mostrarMensajeEditTextMontoTotalSolesError("El monto tiene que ser menor a cien mil");
                            return;
                        }*/
                    }

                    @Override
                    public void onError() {

                    }
                });

    }

    ItemUi itemUi;
    String tipoLlegadaExtra;

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        if (extras == null) return;
        this.itemUi = extras.getParcelable("itemUi");
        Log.d(TAG, "itemUi : " + itemUi.getCodigoPropuesta() + "");
        this.tipoLlegadaExtra = extras.getString("notificacion");


    }

    String tipoOficioPersonal;

    private void initVistasInicial(ItemUi itemUi) {
        Log.d(TAG, "itemUi" + itemUi.getMontoOfico() + "");
        if (view != null) view.mostrarDataInicial(itemUi);
        String paisCodigo = "51";
        itemUi.setPaisCodigo(paisCodigo);
        if (itemUi.getMontoOfico() != null) {
            double montoOficio = Double.parseDouble(itemUi.getMontoOfico());
            if (montoOficio == 0.00) {
                this.tipoOficioPersonal = "oficioNormal";
                if (view != null) {
                    view.mostrarDataInicial(itemUi);
                    view.textViewMostrarInformacion("Ejecución del servicio");
                }
            } else if (montoOficio > 0) {
                this.tipoOficioPersonal = "oficioPer";
                if (view != null) view.textViewMostrarInformacion("Fecha de la entrevista");
                initUseCaseObtenerTipoCambio();
            }

        } else {
            Log.d(TAG, "Llega Nulo");
            // tipoOficioPersonal = "oficioNormal";
            //if (view != null) view.mostrarDataInicial(itemUi);
        }

    }

    double totalDolares, montoNetoOfi, totalComicionOfi, totalSolesOfi, monedaDolarOfi;

    private void initUseCaseObtenerTipoCambio() {
        montoNetoOfi = Double.parseDouble(itemUi.getMontoOfico());
        totalComicionOfi = 0.0;
        totalSolesOfi = Constantes.fijarNumero(montoNetoOfi + totalComicionOfi, 3);
        Log.d(TAG, "itemUi.getPaisCodigo() : " + itemUi.getPaisCodigo());
        handler.execute(obtenerTipoCambioDolar, new ObtenerTipoCambioDolar.RequestValues(itemUi.getPaisCodigo()),
                new UseCase.UseCaseCallback<ObtenerTipoCambioDolar.ResponseValue>() {
                    @Override
                    public void onSuccess(ObtenerTipoCambioDolar.ResponseValue response) {

                        if (response == null) return;
                        if (response.getTipoCambio() == null) return;
                        monedaDolarOfi = Double.parseDouble(response.getTipoCambio());
                        Log.d(TAG, "dolarMonedas : " + monedaDolar);
                        //   monedaDolar = Constantes.fijarNumero(dolarMonedas, 2);
                        totalDolares = Constantes.fijarNumero((montoNetoOfi + totalComicionOfi) / monedaDolarOfi, 3);
                        Log.d(TAG, "totalDolares : " + totalDolares);
                        if (view != null) {
                            view.mostrareditTextMontoNeto(itemUi.getMontoOfico());
                            view.editTextComision("0.0");
                            view.editTextTotalSoles(itemUi.getMontoOfico());
                            view.ediTextTotalDolares(totalDolares + "");
                        }

                    }

                    @Override
                    public void onError() {

                    }
                });
    }
}
