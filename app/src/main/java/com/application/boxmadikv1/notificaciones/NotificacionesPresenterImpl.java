package com.application.boxmadikv1.notificaciones;

import android.os.Bundle;
import android.util.Log;

import com.application.boxmadikv1.api.Api;
import com.application.boxmadikv1.api.RetrofitClient;
import com.application.boxmadikv1.api.response.DefaultNotiTipoPropuesta;
import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.ListaPropuestaEspecialidadResponse;
import com.application.boxmadikv1.api.response.defaultunico.DefaultCotizacionResponse;
import com.application.boxmadikv1.api.response.especialista.GetAceptoCotizacionResponse;
import com.application.boxmadikv1.base.UseCaseHandler;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.EspecialidadesUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.ItemUi;
import com.application.boxmadikv1.modelo.PropuestaEspecialidad;
import com.application.boxmadikv1.notificaciones.entidad.NotificacionesUi;
import com.application.boxmadikv1.seleccionUsuario.SeleccionUserActivity;
import com.application.boxmadikv1.utils.Constantes;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//public abstract class NotificacionesPresenterImpl extends BaseActivityPresenterImpl<NotificacionesView> implements NotificacionesPresenter {
public abstract class NotificacionesPresenterImpl implements NotificacionesPresenter {

    private final String TAG = NotificacionesPresenterImpl.class.getSimpleName();
    Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
    protected Bundle extras;
    public NotificacionesView notificacionesView;
    public UseCaseHandler handler;

    public NotificacionesPresenterImpl(UseCaseHandler handler) {
        this.handler = handler;

    }

    public String codigoUsuario, paisCodigo, tipoNotificacion;

    //  protected Bundle extras;
    @Override
    public void setExtras(Bundle extras) {
        Log.d(TAG, "extrasextrasextrasextras");
        if (extras == null) {
            Log.d(TAG, "extras");
            return;
        }
        //this.extras = extras;
        this.codigoUsuario = extras.getString(SeleccionUserActivity.EXTRA_SELECCION_USER_CODIGO_USU);
        this.paisCodigo = extras.getString(SeleccionUserActivity.EXTRA_SELECCION_USER_CODIGO_PAIS);
        this.tipoNotificacion = extras.getString(SeleccionUserActivity.EXTRA_SELECCION_USER_TIPO_NOTIFICACION);
        Log.d(TAG, "codigoUsuario: " + codigoUsuario +
                "paisCodigo :" + paisCodigo +
                "tipoNotificacion " + tipoNotificacion);

    }

    @Override
    public void attachView(NotificacionesView view) {
        this.notificacionesView = view;
        Log.d(TAG, "No queremos diferentes vistas::attachView ");
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate ");
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart ");
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume ");
    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause ");
    }

    @Override
    public void onStop() {
        Log.d(TAG, "onStop ");
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy ");
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed ");
    }

    @Override
    public void onClickNoti(NotificacionesUi notificacionesUi) {
        if (notificacionesView != null) notificacionesView.mostrarProgressDialog();
        /*Validando Tipo Notificaciones*/
        switch (notificacionesUi.getTipoNotificacion()) {
            /*Cliente*/
            case Constantes.TIPO_NOTIFICACION_CREACION_COTIZACION:
                Log.d(TAG, "onClickNoti + " + paisCodigo +
                        "  / " + codigoUsuario +
                        "  / " + notificacionesUi.getId() +
                        "  / " + notificacionesUi.getTipoNotificacion());
                initRetrofitObtenerDatosNotiCreacionCotizacion(paisCodigo, codigoUsuario, notificacionesUi);
                Log.d(TAG, "TIPO_NOTIFICACION_CREACION_COTIZACION");
                break;
            case Constantes.TIPO_NOTIFICACION_RESPUESTA_NOTIFICACION:
                Log.d(TAG, "TIPO_NOTIFICACION_RESPUESTA_NOTIFICACION");
                initRetrofitObtenerDatosRespuesta(notificacionesUi);
                break;
            /*---------------------------------------*/
            case Constantes.TIPO_NOTIFICACION_MENSAJE_BOXMADIK:
                Log.d(TAG, "TIPO_NOTIFICACION_MENSAJE_BOXMADIK");
                break;
            case Constantes.TIPO_NOTIFICACION_CREACION_PROPUESTA:
                Log.d(TAG, "TIPO_NOTIFICACION_CREACION_PROPUESTA");
                initRetrofitObtenerDatosNotiCreacionPropuesta(paisCodigo, codigoUsuario, notificacionesUi);
                break;
            /*Autorizacion de Pago*/
            case Constantes.TIPO_NOTIFICACION_ACEPTO_COTIZACION:
                Log.d(TAG, "propuestaid: " + notificacionesUi.getCodigoPropuesta() +
                        "codigoUsuarioPropuesta: " + notificacionesUi.getCodigoUsuarioPropuesta() +
                        "codigoCotizacioUsu: " + notificacionesUi.getCodigoUsuarioCotizacion());
                Log.d(TAG, "TIPO_NOTIFICACION_ACEPTO_COTIZACION");
                int tipoCreadaCotiza = 10;
                initRetrofitObtenerDatosAceptoCotizacion(notificacionesUi, tipoCreadaCotiza);
                break;
            case Constantes.TIPO_NOTIFICACION_CREACION_REVOCACION:
                int tipoCreadaRevocacion = 20;
                initRetrofitObtenerDatosAceptoCotizacion(notificacionesUi, tipoCreadaRevocacion);
                Log.d(TAG, "TIPO_NOTIFICACION_CREACION_REVOCACION");
                break;
            case Constantes.TIPO_NOTIFICACION_AUTORIZACION_PAGO:
                if (notificacionesView != null) notificacionesView.ocultarProgressDialog();
                Log.d(TAG, "TIPO_NOTIFICACION_AUTORIZACION_PAGO");
                break;

        }

    }

    private void initRetrofitObtenerDatosAceptoCotizacion(final NotificacionesUi notificacionesUi, final int tipoDataEnviar) {
        /*Cuando se Crea una revocacion */
        Log.d(TAG, "initRetrofitObtenerDatosAceptoCotizacion :"
                + "notificacionesUi.getId() :" + notificacionesUi.getId()
                + "notificacionesUi.getTipoNotificacion() :" + notificacionesUi.getTipoNotificacion()
                + "notificacionesUi.getCodigoPropuesta() :" + notificacionesUi.getCodigoPropuesta()
                + "notificacionesUi.getCodigoUsuarioCotizacion() :" + notificacionesUi.getCodigoUsuarioCotizacion()
                + "notificacionesUi.getCodigoUsuarioPropuesta() :" + notificacionesUi.getCodigoUsuarioPropuesta());

        Call<GetAceptoCotizacionResponse> call = loginService.getAceptoCotizacion(codigoUsuario,
                paisCodigo,
                notificacionesUi.getId(),
                notificacionesUi.getTipoNotificacion(),
                notificacionesUi.getCodigoPropuesta(),
                notificacionesUi.getCodigoUsuarioCotizacion(),
                notificacionesUi.getCodigoUsuarioPropuesta());
        call.enqueue(new Callback<GetAceptoCotizacionResponse>() {
            @Override
            public void onResponse(Call<GetAceptoCotizacionResponse> call, Response<GetAceptoCotizacionResponse> response) {
                GetAceptoCotizacionResponse defaultResponse = response.body();
                String mensaje = "Intentelo de Nuevo";
                if (defaultResponse == null) {
                    if (notificacionesView != null) {
                        notificacionesView.ocultarProgressDialog();
                        notificacionesView.mostrarMensaje(mensaje);
                    }
                    Log.d(TAG, "defaultResponse == null");
                    return;
                } else {
                    if (defaultResponse.getError()) {
                        Log.d(TAG, "defaultResponse.getError()" + defaultResponse.getError());
                        if (notificacionesView != null) {
                            notificacionesView.ocultarProgressDialog();
                            notificacionesView.mostrarMensaje(mensaje);
                        }
                        return;
                    } else {
                        GetAceptoCotizacionResponse.GetAceptoCotizaResponse getAceptoCotizaResponse = defaultResponse.getGetAceptoCotizaResponse();
                        /*validando Estados de Propuesta y Cotizacion*/


                        ItemUi itemUi = new ItemUi();
                        itemUi.setCodigoPropuesta(getAceptoCotizaResponse.getPropuestaCodigo());
                        itemUi.setNombrePropuesta(getAceptoCotizaResponse.getPropuestaTitulo());
                        itemUi.setImagePropuesta(getAceptoCotizaResponse.getRubroImagen());
                        itemUi.setFechaPropuesta(getAceptoCotizaResponse.getPropuestaFecha());
                        itemUi.setDetallesPropuesta(getAceptoCotizaResponse.getPropuestaDetalle());

                        itemUi.setDescripcionRubro(getAceptoCotizaResponse.getRubroDescripcion());

                        itemUi.setDescripcionOficio(getAceptoCotizaResponse.getOficioDescripcion());

                        itemUi.setDescripcionRangoDias(getAceptoCotizaResponse.getRangoDiasDescripcion());

                        itemUi.setDescripcionRangoTurno(getAceptoCotizaResponse.getRangoTurnoDescripcion());

                        itemUi.setCodigoUsuarioPropuesta(getAceptoCotizaResponse.getPropuestaUsuarioCodigo());
                        itemUi.setCotiEstado(getAceptoCotizaResponse.getCotizacionEstado());
                        itemUi.setPropuestaEstado(getAceptoCotizaResponse.getPropuestaEstado());
                        itemUi.setNombreDepartamento(getAceptoCotizaResponse.getDepartamentoNombre());
                        itemUi.setNombreDistrito(getAceptoCotizaResponse.getDistritoNombre());
                        itemUi.setIdCotizacion(getAceptoCotizaResponse.getCotizacionCodigo());


                        Log.d(TAG, "getAceptoCotizaResponse.getCotizacionCodigoUsuario() : " + getAceptoCotizaResponse.getCotizacionCodigoUsuario()+
                                "getAceptoCotizaResponse.getPropuestaUsuarioCodigo() " + getAceptoCotizaResponse.getPropuestaUsuarioCodigo());
                        itemUi.setIdUsuarioCotizacion(getAceptoCotizaResponse.getCotizacionCodigoUsuario());
                        //itemUi.setIdUsuarioCotizacion(getAceptoCotizaResponse.getPropuestaUsuarioCodigo());

                        itemUi.setKeyUser(getAceptoCotizaResponse.getCotizacionCodigoUsuario());
                        //itemUi.setEspecialidadesUiList(llenarEspecialidad(especialidadList));
                        itemUi.setPaisCodigo(paisCodigo);
                        itemUi.setPromedioCotizacion(getAceptoCotizaResponse.getPropuestaPromedioCoti());
                        itemUi.setNumeroCotizacion(getAceptoCotizaResponse.getPropuestaNumCotizacion());


                        if (notificacionesView != null) {

                            notificacionesView.initStartActivityPerfilPropuesta(itemUi, tipoDataEnviar);
                            notificacionesView.ocultarProgressDialog();
                        }

                       /* itemUi.setUsuNombreCliente(especialistaEstadosUi.getUsuNombreCliente());
                        itemUi.setUsuAPellidosCliente(especialistaEstadosUi.getUsuAPellidosCliente());
                        itemUi.setUsuRazonSocialCliente(especialistaEstadosUi.getUsuRazonSocialCliente());
                        itemUi.setUsuRazonSocialCliente(especialistaEstadosUi.getUsuRazonSocialCliente());
                        itemUi.setUsuFotoCliente(especialistaEstadosUi.getUsuFotoCliente());
                        itemUi.setUsuPaisImagenCliente(especialistaEstadosUi.getPaisImagenCliente());
                        String nombreUsu = validarNombreUsuarioNull(itemUi);*/
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAceptoCotizacionResponse> call, Throwable t) {

            }
        });
    }

    private void initRetrofitObtenerDatosRespuesta(NotificacionesUi notificacionesUi) {
        String tipousuario = "cliente";
        DetallesCotizacionUi detallesCotizacionUi = new DetallesCotizacionUi();
        detallesCotizacionUi.setIdPropuesta(notificacionesUi.getCodigoPropuesta());
        detallesCotizacionUi.setUsuarioCodigoPropuesta(notificacionesUi.getCodigoUsuarioPropuesta());
        CotizacionesUi cotizacionesUi = new CotizacionesUi();
        cotizacionesUi.setIdUsuarioCotizacion(notificacionesUi.getCodigoUsuarioCotizacion());
        if (notificacionesView != null) {
            notificacionesView.initStartActivityRespuestaRevocacion(detallesCotizacionUi, cotizacionesUi, tipousuario);
            notificacionesView.ocultarProgressDialog();
        }

    }

    private void initRetrofitObtenerDatosNotiCreacionCotizacion(String paisCodigo, String codigoUsuario, NotificacionesUi notificacionesUi) {

        Log.d(TAG, "initRetrofitObtenerDatosNotiCreacionCotizacion" +
                "paisCodigo : " + paisCodigo +
                "notificacionesUi.getId() : " + notificacionesUi.getId() +
                "notificacionesUi.getTipoNotificacion() : " + notificacionesUi.getTipoNotificacion());
        Call<DefaultCotizacionResponse> call = loginService.obtenerDatoNotificacionCreacionCotiza(codigoUsuario,
                paisCodigo,
                notificacionesUi.getId(),
                notificacionesUi.getTipoNotificacion(),
                notificacionesUi.getUsuCodigoDocu());
        call.enqueue(
                new Callback<DefaultCotizacionResponse>() {
                    @Override
                    public void onResponse(Call<DefaultCotizacionResponse> call, Response<DefaultCotizacionResponse> response) {
                        DefaultCotizacionResponse defaultResponse = response.body();
                        String mensaje = "Intentelo de Nuevo";
                        if (defaultResponse == null) {
                            if (notificacionesView != null) {
                                notificacionesView.ocultarProgressDialog();
                                notificacionesView.mostrarMensaje(mensaje);
                            }
                            Log.d(TAG, "defaultResponse == null");
                            return;
                        } else {
                            if (defaultResponse.getError()) {
                                Log.d(TAG, "defaultResponse.getError()" + defaultResponse.getError());
                                if (notificacionesView != null) {
                                    notificacionesView.ocultarProgressDialog();
                                    notificacionesView.mostrarMensaje(mensaje);
                                }
                                return;
                            } else {

                                DefaultCotizacionResponse.CotizaCreadaResponse cotizaCreadaResponse = defaultResponse.getCotizaCreadaResponse();

                                DetallesCotizacionUi detallesCotizacionUi = new DetallesCotizacionUi();
                                detallesCotizacionUi.setIdPropuesta(cotizaCreadaResponse.getCodigoPropuesta());
                                detallesCotizacionUi.setNombreProyecto(cotizaCreadaResponse.getTituloPropuesta());
                                detallesCotizacionUi.setDetallesPropuesta(cotizaCreadaResponse.getDetallePropuesta());
                                detallesCotizacionUi.setFechaPropuesta(cotizaCreadaResponse.getFechaPropuesta());
                                detallesCotizacionUi.setNombreDepartamento(cotizaCreadaResponse.getDepartamentoDescripcion());
                                detallesCotizacionUi.setNombreDistrito(cotizaCreadaResponse.getDistritoDescripcion());
                                detallesCotizacionUi.setKeyUser(cotizaCreadaResponse.getCodigoUsuPropuesta());
                                detallesCotizacionUi.setImageRubro(cotizaCreadaResponse.getImagenRubro());
                                detallesCotizacionUi.setPaisCodigo(cotizaCreadaResponse.getPais_codigo());
                                detallesCotizacionUi.setUsuarioCodigoPropuesta(cotizaCreadaResponse.getCodigoUsuPropuesta());

                                CotizacionesUi cotizacionesUi = new CotizacionesUi();
                                cotizacionesUi.setIdPropuesta(cotizaCreadaResponse.getCodigoPropuesta());
                                cotizacionesUi.setImagen(cotizaCreadaResponse.getUsu_foto());
                                cotizacionesUi.setIdCotizacion(cotizaCreadaResponse.getCodigoCotizacion());
                                cotizacionesUi.setIdUsuarioCotizacion(cotizaCreadaResponse.getCodigoUsuarioCotizacion());
                                cotizacionesUi.setEstadoCotizacion(cotizaCreadaResponse.getEstadoCotizacion());
                                cotizacionesUi.setEstadoPropuesta(cotizaCreadaResponse.getEstadoPropuesta());

                                cotizacionesUi.setCotiPendiente(cotizaCreadaResponse.getCotiPendiente());
                                cotizacionesUi.setCotiFinalizado(cotizaCreadaResponse.getCotiFinalizado());
                                cotizacionesUi.setCotiAceptado(cotizaCreadaResponse.getCotiAceptado());
                                cotizacionesUi.setMonto(cotizaCreadaResponse.getCoti_monto_inicial());
                                //cotizacionesUi.setNombreEspecialista(cotizaCreadaResponse.getUsu_nombre() + " " + cotizaCreadaResponse.getUsu_apellidos());
                                cotizacionesUi.setUsuRazonSocial(cotizaCreadaResponse.getUsuRazonSocial());
                                Log.d(TAG, "cotizaCreadaResponse.getEstrellasUsuario() : " + cotizaCreadaResponse.getEstrellasUsuario());
                                cotizacionesUi.setPuntuacion(cotizaCreadaResponse.getEstrellasUsuario());
                                cotizacionesUi.setPaisImagen(cotizaCreadaResponse.getPaisImagen());
                                cotizacionesUi.setFecha(Constantes.f_fecha_letras(cotizaCreadaResponse.getCoti_fecha()));
                                cotizacionesUi.setCotiDescripcion(cotizaCreadaResponse.getCotiDescripcion());
                                cotizacionesUi.setNombreEspecialista(validateNombreNull(cotizaCreadaResponse));
                                if (cotizaCreadaResponse.getEstadoPropuesta() == null) return;
                                int tipoEstado = Integer.parseInt(cotizaCreadaResponse.getEstadoPropuesta());
                                detallesCotizacionUi.setTipoEstado(tipoEstado);

                                if (notificacionesView != null) {
                                    notificacionesView.ocultarProgressBar();
                                    notificacionesView.initStartActivityDetallesCotizacionUser(detallesCotizacionUi, cotizacionesUi);
                                    notificacionesView.ocultarProgressDialog();
                                }

                                Log.d(TAG, "CUANDO ACTUALIZO CORRECTAMENTE");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<DefaultCotizacionResponse> call, Throwable t) {
                        Log.d(TAG, "onFailure : ");
                        if (notificacionesView != null) notificacionesView.ocultarProgressDialog();

                    }
                });


    }

    private String validateNombreNull(DefaultCotizacionResponse.CotizaCreadaResponse cotizaCreadaResponse) {
        String nombreUsuario = "";
        if (cotizaCreadaResponse.getUsu_nombre() == null) {
            nombreUsuario = cotizaCreadaResponse.getUsuRazonSocial();
        } else {
            nombreUsuario = cotizaCreadaResponse.getUsu_nombre() + " " + cotizaCreadaResponse.getUsu_apellidos();
        }
        return nombreUsuario;
    }

    private void initRetrofitObtenerDatosNotiCreacionPropuesta(final String paisCodigo, String codigoUsuario, final NotificacionesUi notificacionesUi) {

        Call<DefaultNotiTipoPropuesta> call = loginService.obtenerDatoTipoNotificacion(codigoUsuario,
                paisCodigo,
                notificacionesUi.getId(),
                notificacionesUi.getTipoNotificacion());
        call.enqueue(
                new Callback<DefaultNotiTipoPropuesta>() {
                    @Override
                    public void onResponse(Call<DefaultNotiTipoPropuesta> call, Response<DefaultNotiTipoPropuesta> response) {
                        DefaultNotiTipoPropuesta defaultResponse = response.body();
                        if (defaultResponse == null) {
                            Log.d(TAG, "defaultResponse == null");
                            return;
                        } else {
                            if (defaultResponse.getError()) {
                                Log.d(TAG, "defaultResponse.getError()" + defaultResponse.getError());
                                //  notificacionesView.ocultarProgressBar();
                                if (notificacionesView != null) {
                                    notificacionesView.ocultarProgressBar();
                                    //notificacionesView.initStartActivityDetallesPropuesta(itemUi);
                                }
                                return;
                            } else {
                                DefaultNotiTipoPropuesta.NotiPropuestaResponse notiPropuestaResponse = defaultResponse.getNotiPropuestaResponse();

                                String codigoPropuesta = notiPropuestaResponse.getPri_Codigo();
                                String codigoRubro = notiPropuestaResponse.getRubro_Rub_Codigo();
                                String codigoOficio = notiPropuestaResponse.getOficio_Ofi_codigo();

                                ItemUi itemUi = new ItemUi();

                                itemUi.setCodigoPropuesta(notiPropuestaResponse.getPri_Codigo());
                                itemUi.setNombrePropuesta(notiPropuestaResponse.getPri_Titulo());
                                itemUi.setImagePropuesta(notiPropuestaResponse.getRubroImagen());
                                itemUi.setFechaPropuesta(notiPropuestaResponse.getPri_Fecha());
                                itemUi.setDetallesPropuesta(notiPropuestaResponse.getPri_Detalle());
                                itemUi.setCodigoRubro(notiPropuestaResponse.getRubro_Rub_Codigo());
                                itemUi.setDescripcionRubro(notiPropuestaResponse.getNombreRubro());
                                itemUi.setCodigoOficio(notiPropuestaResponse.getOficio_Ofi_codigo());
                                itemUi.setDescripcionOficio(notiPropuestaResponse.getNombreOficio());
                                itemUi.setCodigoRangoDias(notiPropuestaResponse.getRango_dias_Rad_Item());
                                itemUi.setDescripcionRangoDias(notiPropuestaResponse.getNombreRangoDias());
                                itemUi.setCodigoRangoTurno(notiPropuestaResponse.getRango_turno_Rat_Item());
                                itemUi.setDescripcionRangoTurno(notiPropuestaResponse.getNombreRangoTurno());
                                itemUi.setCodigoRangoPrecio(notiPropuestaResponse.getRango_precio_Ran_Item());
                                itemUi.setCodigoUsuarioPropuesta(notiPropuestaResponse.getUsuarioPropuesta());
                                itemUi.setCotiEstado(notiPropuestaResponse.getPri_Estado());
                                itemUi.setPaisCodigo(paisCodigo);
                                //itemUi.setDescripcionRangoPrecio("0");
                                itemUi.setPromedioCotizacion(notiPropuestaResponse.getPromedio_coti());
                                itemUi.setNumeroCotizacion(notiPropuestaResponse.getNum_cotizacion());
                                itemUi.setNombreDepartamento(notiPropuestaResponse.getDepa_Desc());
                                itemUi.setNombreDistrito(notiPropuestaResponse.getDist_Desc());
                                //itemUi.setUsuRazonSocialCliente(n);
                               /* llenarListaEspecialidad(codigoPropuesta,
                                        codigoRubro, codigoOficio, paisCodigo, itemUi);*/
                                if (notificacionesView != null) {
                                    notificacionesView.ocultarProgressBar();
                                    notificacionesView.initStartActivityDetallesPropuesta(itemUi);
                                }
                                Log.d(TAG, "CUANDO ACTUALIZO CORRECTAMENTE");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<DefaultNotiTipoPropuesta> call, Throwable t) {

                    }
                });

    }

    private void llenarListaEspecialidad(String codigoPropuesta, String codigoRubro, String codigoOficio, String paisCodigo, final ItemUi itemUi) {

        Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        Call<ListaPropuestaEspecialidadResponse> call = loginService.obtenerListaPropuestaEspecialidad(Integer.parseInt(codigoPropuesta),
                Integer.parseInt(codigoRubro),
                Integer.parseInt(codigoOficio),
                Integer.parseInt(paisCodigo));
        call.enqueue(new Callback<ListaPropuestaEspecialidadResponse>() {
            @Override
            public void onResponse(Call<ListaPropuestaEspecialidadResponse> call, Response<ListaPropuestaEspecialidadResponse> response) {
                ListaPropuestaEspecialidadResponse body = response.body();

                if (body == null) return;
                Log.d(TAG, "resultado == NULL");
                List<PropuestaEspecialidad> especialidadList = body.getPropuestaEspecialidad();
                if (especialidadList == null) {
                    Log.d(TAG, "especialidadList ");
                    if (notificacionesView != null) {
                        notificacionesView.ocultarProgressBar();
                        notificacionesView.initStartActivityDetallesPropuesta(itemUi);
                    }
                } else {
                    Log.d(TAG, "WSP ELSE");
                    List<EspecialidadesUi> especialidadesUiList = new ArrayList<>();
                    for (PropuestaEspecialidad propuestaEspecialidad
                            : especialidadList) {
                        EspecialidadesUi especialidadesUi = new EspecialidadesUi();
                        especialidadesUi.setCodigoEspecialidad(String.valueOf(propuestaEspecialidad.getPE_Codigo()));
                        especialidadesUi.setDescripcionEspecialidad(propuestaEspecialidad.getPE_descripcion());
                        especialidadesUiList.add(especialidadesUi);
                    }
                    Log.d(TAG, "WSP ELSE : " + especialidadesUiList.size());
                    itemUi.setEspecialidadesUiList(especialidadesUiList);
                    if (notificacionesView != null) {
                        notificacionesView.ocultarProgressBar();
                        notificacionesView.initStartActivityDetallesPropuesta(itemUi);
                    }
                    /*itemUi.setEspecialidadesUiList(especialidadesUiList);
                    if (notificacionesView != null) {
                        notificacionesView.ocultarProgressBar();
                        notificacionesView.initStartActivityDetallesPropuesta(itemUi);
                    }*/
                }
            }

            @Override
            public void onFailure(Call<ListaPropuestaEspecialidadResponse> call, Throwable t) {
                Log.d(TAG, "WSP if No tiene Especialidades");
            }
        });
    }

    private List<EspecialidadesUi> llenarEspecialidad(String codigoPropuesta, String codigoRubro,
                                                      String codigoOficio, String paisCodigo) {
        final List<EspecialidadesUi> especialidadesUiList = new ArrayList<>();

        int propuestaCodigo = Integer.parseInt(codigoPropuesta);
        int rubroCodigo = Integer.parseInt(codigoRubro);
        int oficioCodigo = Integer.parseInt(codigoOficio);

        Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
        Call<ListaPropuestaEspecialidadResponse> call = loginService.obtenerListaPropuestaEspecialidad(propuestaCodigo,
                rubroCodigo,
                oficioCodigo,
                Integer.parseInt(paisCodigo));
        call.enqueue(new Callback<ListaPropuestaEspecialidadResponse>() {
            @Override
            public void onResponse(Call<ListaPropuestaEspecialidadResponse> call, Response<ListaPropuestaEspecialidadResponse> response) {
                ListaPropuestaEspecialidadResponse body = response.body();

                if (body == null) return;
                Log.d(TAG, "resultado == NULL");
                List<PropuestaEspecialidad> especialidadList = body.getPropuestaEspecialidad();
                if (especialidadList == null) {
                    Log.d(TAG, "especialidadList ");
                } else {
                    Log.d(TAG, "WSP ELSE");

                    for (PropuestaEspecialidad propuestaEspecialidad
                            : especialidadList) {
                        EspecialidadesUi especialidadesUi = new EspecialidadesUi();
                        especialidadesUi.setCodigoEspecialidad(String.valueOf(propuestaEspecialidad.getPE_Codigo()));
                        especialidadesUi.setDescripcionEspecialidad(propuestaEspecialidad.getPE_descripcion());
                        especialidadesUiList.add(especialidadesUi);
                    }

                }
            }

            @Override
            public void onFailure(Call<ListaPropuestaEspecialidadResponse> call, Throwable t) {
                Log.d(TAG, "WSP if No tiene Especialidades");
            }
        });

        return especialidadesUiList;
    }


    @Override
    public void onClickOptionsLeido(final NotificacionesUi notificacionesUi) {
        Log.d(TAG, "onClickOptionsLeido : " + notificacionesUi.getId());
        Log.d(TAG, "onClickOptionsLeido : " + paisCodigo +
                "notificacionesUi.getCodigoTipoNotificacion() :" + notificacionesUi.getCodigoTipoNotificacion() +
                "notificacionesUi.getCodigoGrupoNotificacion() :" + notificacionesUi.getCodigoGrupoNotificacion() +
                "codigoUsuario :" + codigoUsuario +
                "notificacionesUi.getCodigoPropuesta() :" + notificacionesUi.getCodigoPropuesta()
        );
        Api loginService = RetrofitClient.createService(Api.class, Constantes.BASE_URL_REMOTE);
       /* loginService.actualizarNotificacionesLeidas(paisCodigo,
                notificacionesUi.getCodigoTipoNotificacion(),
                notificacionesUi.getCodigoGrupoNotificacion(),
                codigoUsuario,
                notificacionesUi.getCodigoPropuesta()
        ).*/
        loginService.actualizarNotificacion(notificacionesUi.getId()
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
                        notificacionesUi.setEstadoLeido(true);
                        if (notificacionesView != null)
                            notificacionesView.actualizarVistaNotificacion(notificacionesUi);
                        Log.d(TAG, "ACTUALIZO CORRECTAMENTE");
                    }
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                String mensaje = "Ocurrio Algun Error";
                Log.d(TAG, "mensaje : " + mensaje);

                //Log.d(TAG, "onFailure : " + t.getMessage().toString());
            }
        });


    }
}
